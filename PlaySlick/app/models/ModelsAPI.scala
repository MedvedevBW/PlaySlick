package models

import java.util.Date

import play.api.libs.json.Json

// public JSON API models

case class AuthorAPI(
  authorId: Int,
  name: String,
  surname: String,
  middlename: Option[String],
  birthday: Date
)

case class BookAPI(
  bookId: Int,
  title: String,
  subtitle: Option[String],
  pubDate: Date,
  pubHouse: String,
  authors: List[AuthorAPI]
)

case class MetaAPI(total: Int, pageSize: Int, pageNumber: Int)

object AuthorAPI {
  implicit val authorFormat = Json.format[AuthorAPI]
}

object BookAPI {
  implicit val bookFormat = Json.format[BookAPI]
}

object MetaAPI {
  implicit val metaFormat = Json.format[MetaAPI]
}