package entities

import play.api.libs.json.Json

case class Order (id: Option[Long], date: Long, name: String, age: Int)

object Order {
  implicit val format = Json.format[Order]
}
