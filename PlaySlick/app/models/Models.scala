package models

import java.util.Date

// representations od table rows

case class AuthorRow(
  authorId: Int,
  name: String,
  surname: String,
  middlename: String,
  birthday: Date
)

case class BookRow(
  bookId: Int,
  title: String,
  subtitle: String,
  pubDate: Date,
  pubHouse: String
)

case class AuthorBookRow(authorId: Int, bookId: Int)

