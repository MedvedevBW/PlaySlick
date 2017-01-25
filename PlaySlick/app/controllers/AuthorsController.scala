package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json

import scala.concurrent.Future
import models._
import dao.AuthorDAO
import models.inline.InlineModelAuthor

class AuthorsController @Inject() (authorDAO: AuthorDAO) extends Controller {

  def findAll(page: Int, pageSize: Int, orderBy: Int, filter: String = "") = Action.async { implicit request =>

    val count = authorDAO.count(filter)
    val authors = authorDAO.findAll(page, pageSize, orderBy, "%" + filter + "%")
    for {
      a <- count
      b <- authors
    } yield Ok(Json.toJson(InlineModelAuthor(MetaAPI(a, pageSize, page), b)))

  }

  def findById(id: Int) = Action.async { implicit request =>

    authorDAO.findById(id) map {
      case Some(author) => Ok(Json.toJson(author))
      case _ => BadRequest(Json.obj("error" -> s"Author with id $id doesn't exist"))
    }
  }

  def delete(id: Int) = Action.async { implicit request =>

    authorDAO.delete(id) map {
      _ => Ok(Json.obj("success" -> "deleted"))
    }
  }

  def create = Action.async(parse.json) { implicit request =>

    request.body.validate[AuthorAPI].map {
      authorAPI =>
        authorDAO.create(authorAPI) map {
          _ => Ok(Json.obj("success" -> "created"))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

  def update(id: Int) = Action.async(parse.json) { implicit request =>

    request.body.validate[AuthorAPI].map {
      authorAPI =>
        authorDAO.update(id, authorAPI) map {
          _ => Ok(Json.obj("success" -> "updated"))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

}
