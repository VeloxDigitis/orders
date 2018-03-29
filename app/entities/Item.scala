package entities

import play.api.libs.json.Json

case class Item (orderId: Long, color: String, size: String)

object Item {
  implicit val format = Json.format[Item]
}