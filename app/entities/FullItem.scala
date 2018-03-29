package entities

import play.api.libs.json.Json

case class FullItem (color: String, size: String)

object FullItem {
  implicit val format = Json.format[FullItem]
}

