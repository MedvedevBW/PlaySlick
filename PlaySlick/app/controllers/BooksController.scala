package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import scala.concurrent.Future
import models._
import dao._

/**
 * Created by medvedev_vv on 24.01.17.
 */
class BooksController @Inject() (bookDAO: BookDAO) extends Controller {

  def findAll = Action.async { implicit request =>

    bookDAO.findAll map {
      books => Ok(Json.toJson(books))
    }
  }

  def findById(id: Int) = Action.async { implicit request =>

    bookDAO.findById(id) map {
      case Some(book) => Ok(Json.toJson(book))
      case _ => BadRequest(Json.obj("error" -> s"Book with id $id doesn't exist"))
    }
  }

  def delete(id: Int) = Action.async { implicit request =>

    bookDAO.delete(id) map {
      numDeleted => Ok(Json.obj("count" -> numDeleted))
    }
  }

  def create = Action.async(parse.json) { implicit request =>

    request.body.validate[BookAPI].map {
      bookAPI =>
        bookDAO.create(bookAPI) map {
          bookID => Ok(Json.obj("id" -> bookID))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

  def update(id: Int) = Action.async(parse.json) { implicit request =>

    request.body.validate[BookAPI].map {
      bookAPI =>
        bookDAO.update(id, bookAPI) map {
          numUpdated => Ok(Json.obj("count" -> numUpdated))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

}
