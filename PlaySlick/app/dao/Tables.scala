package dao

import models._

import java.util.Date

trait Tables {

  protected val driver: slick.driver.JdbcProfile
  import driver.api._

  import slick.model.ForeignKeyAction
  import slick.jdbc.{ GetResult => GR }

  /**
   * AUTHOR
   */
  implicit def GetResultAuthorRow(implicit e0: GR[Int], e1: GR[String], e3: GR[Date]): GR[AuthorRow] = GR {
    prs =>
      import prs._
      AuthorRow.tupled((<<[Int], <<[String], <<[String], <<[Option[String]], <<[Date]))
  }
  /** Table description of table author. Objects of this class serve as prototypes for rows in queries. */
  class Author(_tableTag: Tag) extends Table[AuthorRow](_tableTag, "author") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def * = (authorId, name, surname, middlename, birthday) <> (AuthorRow.tupled, AuthorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(authorId), Rep.Some(name), Rep.Some(surname), Rep.Some(middlename), Rep.Some(birthday)).shaped.<>({ r => import r._; _1.map(_ => AuthorRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column author_id SqlType(INT), AutoInc, PrimaryKey */
    val authorId: Rep[Int] = column[Int]("author_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100, varying = true))
    /** Database column surname SqlType(VARCHAR), Length(100,true) */
    val surname: Rep[String] = column[String]("surname", O.Length(100, varying = true))
    /** Database column middlename SqlType(VARCHAR), Length(100,true) */
    val middlename: Rep[Option[String]] = column[String]("middlename", O.Length(100)).?
    /** Database column birthday SqlType(date) */
    val birthday: Rep[Date] = column[Date]("birthday")
  }
  /** Collection-like TableQuery object for table Author */
  lazy val Author = new TableQuery(tag => new Author(tag))

  /**
   * BOOK
   */
  implicit def GetResultBookRow(implicit e0: GR[Int], e1: GR[String], e3: GR[Date]): GR[BookRow] = GR {
    prs =>
      import prs._
      BookRow.tupled((<<[Int], <<[String], <<[Option[String]], <<[Date], <<[String]))
  }
  /** Table description of table book. Objects of this class serve as prototypes for rows in queries. */
  class Book(_tableTag: Tag) extends Table[BookRow](_tableTag, "book") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def * = (bookId, title, subtitle, pubDate, pubHouse) <> (BookRow.tupled, BookRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(bookId), Rep.Some(title), Rep.Some(subtitle), Rep.Some(pubDate), Rep.Some(pubHouse)).shaped.<>({ r => import r._; _1.map(_ => BookRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column book_id SqlType(INT), AutoInc, PrimaryKey */
    val bookId: Rep[Int] = column[Int]("book_id", O.AutoInc, O.PrimaryKey)
    /** Database column title SqlType(VARCHAR), Length(100,true) */
    val title: Rep[String] = column[String]("title", O.Length(100, varying = true))
    /** Database column subtitle SqlType(VARCHAR), Length(100,true) */
    val subtitle: Rep[Option[String]] = column[String]("subtitle", O.Length(100)).?
    /** Database column pubDate SqlType(date) */
    val pubDate: Rep[Date] = column[Date]("pubDate")
    /** Database column pubHouse SqlType(VARCHAR), Length(100,true) */
    val pubHouse: Rep[String] = column[String]("pubHouse", O.Length(100, varying = true))
  }
  /** Collection-like TableQuery object for table Book */
  lazy val Book = new TableQuery(tag => new Book(tag))

  /**
   * BOOK-AUTHOR
   */
  implicit def GetResultAuthorBookRow(implicit e0: GR[Int]): GR[AuthorBookRow] = GR {
    prs =>
      import prs._
      AuthorBookRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table author_book. Objects of this class serve as prototypes for rows in queries. */
  class AuthorBook(_tableTag: Tag) extends Table[AuthorBookRow](_tableTag, "author_book") {
    def * = (authorId, bookId) <> (AuthorBookRow.tupled, AuthorBookRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(authorId), Rep.Some(bookId)).shaped.<>({ r => import r._; _1.map(_ => AuthorBookRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column author_id SqlType(INT) */
    val authorId: Rep[Int] = column[Int]("author_id")
    /** Database column book_id SqlType(INT) */
    val bookId: Rep[Int] = column[Int]("book_id")

    /** Primary key of AuthorBook (database name author_book_PK) */
    val pk = primaryKey("author_book_PK", (authorId, bookId))

    /** Foreign key referencing Author (database name author_book_author_fk) */
    lazy val authorFk = foreignKey("author_author_book_fk", authorId, Author)(r => r.authorId, onUpdate = ForeignKeyAction.Cascade, onDelete = ForeignKeyAction.Cascade)
    /** Foreign key referencing Book (database name book_author_book_fk) */
    lazy val bookFk = foreignKey("book_author_book_fk", bookId, Book)(r => r.bookId, onUpdate = ForeignKeyAction.Cascade, onDelete = ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table AuthorBook */
  lazy val AuthorBook = new TableQuery(tag => new AuthorBook(tag))

}