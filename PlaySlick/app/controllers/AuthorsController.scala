package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import scala.concurrent.Future
import models._
import dao.AuthorDAO
/**
 * Created by medvedev_vv on 24.01.17.
 */
class AuthorsController @Inject() (authorDAO: AuthorDAO) extends Controller {

  def findAll = Action.async { implicit request =>

    authorDAO.findAll map {
      authors => Ok(Json.toJson(authors))
    }
  }

  def findById(id: Int) = Action.async { implicit request =>

    authorDAO.findById(id) map {
      case Some(author) => Ok(Json.toJson(author))
      case _ => BadRequest(Json.obj("error" -> s"Author with id $id doesn't exist"))
    }
  }

  def delete(id: Int) = Action.async { implicit request =>

    authorDAO.delete(id) map {
      numDeleted => Ok(Json.obj("count" -> numDeleted))
    }
  }

  def create = Action.async(parse.json) { implicit request =>

    request.body.validate[AuthorAPI].map {
      authorAPI =>
        authorDAO.create(authorAPI) map {
          authorId => Ok(Json.obj("id" -> authorId))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

  def update(id: Int) = Action.async(parse.json) { implicit request =>

    request.body.validate[AuthorAPI].map {
      authorAPI =>
        authorDAO.update(id, authorAPI) map {
          numUpdated => Ok(Json.obj("count" -> numUpdated))
        }
    } recoverTotal { t =>
      Future.successful(BadRequest(Json.obj("error" -> "Wrong JSON format")))
    }
  }

}
