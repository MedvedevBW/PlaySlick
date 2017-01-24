package dao

import javax.inject.Inject
import javax.inject.Singleton
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import models._

@Singleton()
class BookDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {

  val profile = driver
  import driver.api._

  def findAll(implicit ec: ExecutionContext): Future[List[BookAPI]] = {

    db.run(getBooksQuery().result) map {
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

  def findById(id: Int)(implicit ec: ExecutionContext): Future[Option[BookAPI]] = {

    db.run(getBooksQuery(Option(id)).result) map {
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

  /**
   * common query for findAll and findById
   */
  private def getBooksQuery(maybeId: Option[Int] = None) = {

    val booksQuery = maybeId match {
      case None => Book
      case Some(id) => Book.filter(_.bookId === id)
    }

    val withAuthorQuery = for {
      ((book, _), author) <- booksQuery.join(AuthorBook).on(_.bookId === _.bookId).
        joinLeft(Author).on(_._2.authorId === _.authorId)
    } yield (book, author)

    withAuthorQuery
  }

  def create(book: BookAPI)(implicit ec: ExecutionContext): Future[Int] = {

    val bookRow = BookRow(bookId = 0, title = book.title, subtitle = book.subtitle, pubDate = book.pubDate, pubHouse = book.pubHouse)
    db.run((Book returning Book.map(_.bookId)) += bookRow) flatMap {
      bookId =>
        val authorLinkRows = book.authors.map(a => AuthorBookRow(bookId = bookId, authorId = a.authorId))
        val queries = DBIO.seq(
          AuthorBook ++= authorLinkRows
        )
        db.run(queries).map(_ => bookId) // return bookId
    }
  }

  def delete(id: Int)(implicit ec: ExecutionContext): Future[Int] = {

    db.run(Book.filter(_.bookId === id).delete)
  }

  def update(id: Int, book: BookAPI)(implicit ec: ExecutionContext): Future[Int] = {

    findById(id) flatMap {
      case None => Future.successful(0) // not found
      case Some(oldBook) => {

        val deleteOldBook = Book.filter(_.bookId === id).delete
        val newBook = BookRow(bookId = oldBook.bookId, title = book.title, subtitle = book.subtitle, pubDate = book.pubDate, pubHouse = book.pubHouse)
        val insertNewBook = Book += newBook

        val deleteOldAuthor = AuthorBook.filter(_.bookId === id).delete
        val newAuthors = book.authors.map(a => AuthorBookRow(bookId = oldBook.bookId, authorId = a.authorId))
        val insertNewAuthors = AuthorBook ++= newAuthors

        val queries = DBIO.seq(
          deleteOldBook,
          insertNewBook,
          deleteOldAuthor,
          insertNewAuthors
        )
        db.run(queries).map(_ => 1)
      }
    }
  }

}
