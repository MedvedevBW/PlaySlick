package dao

import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }
import models._

@Singleton()
class BookDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {

  val profile = driver
  import driver.api._

  def count(filter: String, authorId: Option[Int]): Future[Int] = {
    val countQuery = authorId match {
      case None => db.run(Book.filter { book => book.title.toLowerCase.like(filter.toLowerCase) }.length.result)
      case Some(authorId) => db.run(Book.filter { book => book.title.toLowerCase.like(filter.toLowerCase) }.join(
        AuthorBook.filter(_.authorId === authorId)
      ).on(_.bookId === _.bookId).length.result)
    }
    countQuery
  }

  def findById(id: Int)(implicit ec: ExecutionContext): Future[Option[BookAPI]] = {

    db.run(getBooksQuery(Option(id), "%").result) map {
      dataTuples =>
        val groupedByBook = dataTuples.groupBy(_._1)
        groupedByBook.map {
          case (bookRow, tuples) =>
            val authors = tuples.flatMap(_._2).distinct.map { a => // notice flatMap
              AuthorAPI(
                authorId = a.authorId,
                name = a.name,
                surname = a.surname,
                middlename = a.middlename,
                birthday = a.birthday
              )
            }
            BookAPI(
              bookId = bookRow.bookId,
              title = bookRow.title,
              subtitle = bookRow.subtitle,
              pubDate = bookRow.pubDate,
              pubHouse = bookRow.pubHouse,
              authors = authors.toList
            )
        }.headOption
    }
  }

  def findAllOrByAuthorId(page: Int, pageSize: Int, orderBy: Int, filter: String = "%", authorId: Option[Int] = None)(implicit ec: ExecutionContext): Future[List[BookAPI]] = {

    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    val booksQuery = authorId match {
      case None => getBooksQuery(None, filter)
      case Some(authorId) => getBooksByAuthorQuery(authorId, filter)
    }

    val offset = pageSize * page

    val sort = orderBy match {
      case -5 => booksQuery.sortBy(_._1.pubDate.desc)
      case -4 => booksQuery.sortBy(_._1.pubHouse.desc)
      case -3 => booksQuery.sortBy(_._1.subtitle.desc)
      case -2 => booksQuery.sortBy(_._1.title.desc)
      case 2 => booksQuery.sortBy(_._1.title)
      case 3 => booksQuery.sortBy(_._1.subtitle)
      case 4 => booksQuery.sortBy(_._1.pubHouse)
      case 5 => booksQuery.sortBy(_._1.pubDate)
      case _ => booksQuery
    }

    db.run(sort.drop(offset).take(pageSize).result) map {
      dataTuples =>
        val groupedByBook = dataTuples.groupBy(_._1)
        groupedByBook.map {
          case (bookRow, tuples) =>
            val authors = tuples.flatMap(_._2).distinct.map { a =>
              AuthorAPI(
                authorId = a.authorId,
                name = a.name,
                surname = a.surname,
                middlename = a.middlename,
                birthday = a.birthday
              )
            }
            BookAPI(
              bookId = bookRow.bookId,
              title = bookRow.title,
              subtitle = bookRow.subtitle,
              pubDate = bookRow.pubDate,
              pubHouse = bookRow.pubHouse,
              authors = authors.toList
            )
        }.toList
    }
  }
  /**
   * common query for findByAuthorId
   */
  private def getBooksByAuthorQuery(authorId: Int, inFilter: String) = {
    val booksQuery = Book.filter { book => book.title.toLowerCase.like(inFilter.toLowerCase) }

    val bookWithAuthorQuery = for {
      (((_, book), _), author) <- AuthorBook.filter(_.authorId === authorId).join(
        booksQuery
      ).on(_.bookId === _.bookId).join(
        AuthorBook
      ).on(_._2.bookId === _.bookId).joinLeft(
        Author
      ).on(_._2.authorId === _.authorId)
    } yield (book, author)

    bookWithAuthorQuery
  }

  /**
   * common query for findAll and findById
   */
  private def getBooksQuery(maybeId: Option[Int] = None, inFilter: String) = {

    val booksQuery = maybeId match {
      case None => Book.filter { book => book.title.toLowerCase.like(inFilter.toLowerCase) }
      case Some(id) => Book.filter(_.bookId === id)
    }

    val withAuthorQuery = for {
      ((book, _), author) <- booksQuery.join(AuthorBook).on(_.bookId === _.bookId).
        joinLeft(Author).on(_._2.authorId === _.authorId)
    } yield (book, author)

    withAuthorQuery
  }

  def create(book: BookAPI)(implicit ec: ExecutionContext): Future[Unit] = {

    val bookRow = BookRow(bookId = 0, title = book.title, subtitle = book.subtitle, pubDate = book.pubDate, pubHouse = book.pubHouse)
    db.run((Book returning Book.map(_.bookId)) += bookRow) flatMap {
      bookId =>
        val authorLinkRows = book.authors.map(a => AuthorBookRow(bookId = bookId, authorId = a.authorId))
        val queries = DBIO.seq(
          AuthorBook ++= authorLinkRows
        )
        db.run(queries).map(_ => ())
    }
  }

  def delete(id: Int)(implicit ec: ExecutionContext): Future[Unit] = {

    db.run(Book.filter(_.bookId === id).delete).map(_ => ())
  }

  def update(id: Int, book: BookAPI)(implicit ec: ExecutionContext): Future[Int] = {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    findById(id) flatMap {
      case None => Future.successful(0) // not found
      case Some(oldBook) => {

        val updateBookQuery = Book.filter(_.bookId === id).map(
          r => (r.title, r.subtitle.get, r.pubDate, r.pubHouse)
        ).update(book.title, book.subtitle.get, book.pubDate, book.pubHouse)

        val deleteOldAuthorBook = AuthorBook.filter(_.bookId === id).delete
        val newAuthorsBook = book.authors.map(a => AuthorBookRow(bookId = oldBook.bookId, authorId = a.authorId))
        val insertNewAuthorsBook = AuthorBook ++= newAuthorsBook

        val queries = DBIO.seq(
          updateBookQuery,
          deleteOldAuthorBook,
          insertNewAuthorsBook
        )
        db.run(queries).map(_ => 1)
      }
    }
  }
}
