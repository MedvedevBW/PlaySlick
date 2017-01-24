package dao

/**
 * Created by medvedev_vv on 23.01.17.
 *
 *
 */
import java.util.Date

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import models._
import javax.inject.Singleton

@Singleton()
class AuthorDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends Tables with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  /**
   * @param ec
   * @return all addresses
   */
  def findAll(implicit ec: ExecutionContext): Future[List[AuthorAPI]] = {

    val query = Author
    // TableQuery[Author]
    // query.filter ... .drop ... .take
    val action = query.result
    // Action...
    val futureAuthors = db.run(action) // Future[Seq[AddressRow]]

    futureAuthors.map(
      _.map {
      a => AuthorAPI(authorId = a.authorId, name = a.name, surname = a.surname, middlename = a.middlename, birthday = a.birthday)
    }.toList // or query.to[List]
    )
  }

  /**
   * @param id
   * @param ec
   * @return author by id
   */
  def findById(id: Int)(implicit ec: ExecutionContext): Future[Option[AuthorAPI]] = {

    db.run(Author.filter(_.authorId === id).result).map(_.headOption).map {
      _.map {
        a => AuthorAPI(authorId = a.authorId, name = a.name, surname = a.surname, middlename = a.middlename, birthday = a.birthday)
      }
    }
  }

  /**
   * @param author
   * @param ec
   * @return generated AutoInc authorId
   */
  def create(author: AuthorAPI)(implicit ec: ExecutionContext): Future[Int] = {

    val authorRow = AuthorRow(authorId = 0, name = author.name, surname = author.surname, middlename = author.middlename, birthday = author.birthday)
    db.run((Author returning Author.map(_.authorId)) += authorRow)
  }

  /**
   * @param id
   * @param ec
   * @return num of deleted rows
   */
  def delete(id: Int)(implicit ec: ExecutionContext): Future[Int] = {

    db.run(Author.filter(_.authorId === id).delete)
  }

  /**
   * updates author
   */
  def update(id: Int, author: AuthorAPI)(implicit ec: ExecutionContext): Future[Int] = {
    findById(id) flatMap {
      case None => Future.successful(0) // not found
      case Some(oldAuthor) => {
        val deleteOldAuthor = Author.filter(_.authorId === id).delete
        val newAuthor = AuthorRow(authorId = oldAuthor.authorId, name = author.name, surname = author.surname, middlename = author.middlename, birthday = author.birthday)
        val insertNewAuthor = Author += newAuthor

        val queries = DBIO.seq(
          deleteOldAuthor,
          insertNewAuthor
        )
        db.run(queries).map(_ => 1)
      }
    }
  }
}
