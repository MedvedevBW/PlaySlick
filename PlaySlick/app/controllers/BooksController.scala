package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json

import scala.concurrent.Future
import models._
import dao._
import models.inline.InlineModelBook

class BooksController @Inject() (bookDAO: BookDAO) extends Controller {

  def findAll(page: Int, pageSize: Int, orderBy: Int, filter: String, authId: Option[Int]) = Action.async { implicit request =>

    val count = bookDAO.count(filter)
    val books = bookDAO.findAllOrByAuthorId(page, pageSize, orderBy, "%" + filter + "%", authId)
    for {
      a <- count
      b <- books
    } yield Ok(Json.toJson(InlineModelBook(MetaAPI(a, pageSize, page), b)))
  }

  def findById(id: Int) = Action.async { implicit request =>

    bookDAO.findById(id) map {
      case Some(book) => Ok(Json.toJson(book))
      case _ => BadRequest(Json.obj("error" -> s"Book doesn't exist"))
    }
  }

  def delete(id: Int) = Action.async { implicit request =>

    bookDAO.delete(id) map {
      _ => Ok(Json.obj("success" -> "deleted"))
    }
  }

  def create = Action.async(parse.json) { implicit request =>

    request.body.validate[BookAPI].map {
      bookAPI =>
        bookDAO.create(bookAPI) map {
          _ => Ok(Json.obj("success" -> "created"))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

  def update(id: Int) = Action.async(parse.json) { implicit request =>

    request.body.validate[BookAPI].map {
      bookAPI =>
        bookDAO.update(id, bookAPI) map {
          _ => Ok(Json.obj("success" -> "updated"))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

}
