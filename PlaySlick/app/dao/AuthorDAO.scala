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

  def count(filter: String): Future[Int] = {
    db.run(Author.filter { author => author.surname.toLowerCase.like(filter.toLowerCase) }.length.result)
  }
  /**
   * @param ec
   * @return all addresses
   */
  def findAll(page: Int, pageSize: Int, orderBy: Int, inFilter: String)(implicit ec: ExecutionContext): Future[List[AuthorAPI]] = {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    val offset = pageSize * page

    val authorsQuery = Author.filter { author => author.name.toLowerCase.like(inFilter.toLowerCase) }

    val test: String = "hi"

    val sortQuery = orderBy match {
      case -5 => authorsQuery.sortBy(_.birthday.desc)
      case -4 => authorsQuery.sortBy(_.middlename.desc)
      case -3 => authorsQuery.sortBy(_.surname.desc)
      case -2 => authorsQuery.sortBy(_.name.desc)
      case 2 => authorsQuery.sortBy(_.name)
      case 3 => authorsQuery.sortBy(_.surname)
      case 4 => authorsQuery.sortBy(_.middlename)
      case 5 => authorsQuery.sortBy(_.birthday)
      case _ => authorsQuery
    }

    // TableQuery[Author]
    // query.filter ... .drop ... .take
    val action = sortQuery.drop(offset).take(pageSize).result
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
  def create(author: AuthorAPI)(implicit ec: ExecutionContext): Future[Unit] = {

    val authorRow = AuthorRow(authorId = 0, name = author.name, surname = author.surname, middlename = author.middlename, birthday = author.birthday)
    db.run((Author returning Author.map(_.authorId)) += authorRow).map(_ => ())
  }

  /**
   * @param id
   * @param ec
   * @return num of deleted rows
   */
  def delete(id: Int)(implicit ec: ExecutionContext): Future[Unit] = {

    db.run(Author.filter(_.authorId === id).delete) map (_ => ())
  }

  /**
   * updates author
   */
  def update(id: Int, author: AuthorAPI)(implicit ec: ExecutionContext): Future[Int] = {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))
    findById(id) flatMap {
      case None => Future.successful(0) // not found
      case Some(oldAuthor) => {
        val updateQuery = Author.filter(_.authorId === id).map(
          r => (r.name, r.surname, r.middlename.get, r.birthday)
        ).update(author.name, author.surname, author.middlename.get, author.birthday)

        db.run(updateQuery).map(_ => 1)
      }
    }
  }
}
