package repository

import javax.inject.{Inject, Singleton}

import entities.Item
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ItemRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, orderRepository: OrderRepository)(implicit ec: ExecutionContext) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class ItemsTable(tag: Tag) extends Table[Item](tag, "ITEMS") {
    def id      = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def orderId = column[Long]("orderId")
    def color   = column[String]("color")
    def size    = column[String]("size")

    def * = (id.?, orderId,color, size) <> ((Item.apply _).tupled, Item.unapply)
  }

  val items = TableQuery[ItemsTable]

  def getOrderItems(orderId: Long): Future[Seq[Item]] = db.run {
    items.filter(i => i.id === orderId).result
  }

  def addItem(item: Item): Future[Item] = db.run {
    (items returning
      items.map(_.id) into
      ((item, id) => item.copy(id = Some(id)))) += item
  }

}