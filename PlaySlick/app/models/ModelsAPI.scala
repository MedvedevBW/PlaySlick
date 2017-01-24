package models

import java.util.Date

import play.api.libs.json.Json

// public JSON API models

case class AuthorAPI(
  authorId: Int,
  name: String,
  surname: String,
  middlename: String,
  birthday: Date
)

case class BookAPI(
  bookId: Int,
  title: String,
  subtitle: String,
  pubDate: Date,
  pubHouse: String,
  authors: List[AuthorAPI]
)

object AuthorAPI {
  implicit val authorFormat = Json.format[AuthorAPI]
}

object BookAPI {
  implicit val bookFormat = Json.format[BookAPI]
}

