package entities

import play.api.libs.json.Json

case class FullOrder (name: String, age: Int, items: Seq[Item])

object FullOrder {
  implicit val format = Json.format[FullOrder]
}


