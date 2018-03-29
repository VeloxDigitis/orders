package repository

import javax.inject.{Inject, Singleton}

import entities.Order
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class OrdersTable(tag: Tag) extends Table[Order](tag, "ORDERS") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def date = column[String]("date")
    def name = column[String]("name")
    def age = column[Int]("age")

    def * = (id.?, date, name, age) <> ((Order.apply _).tupled, Order.unapply)
  }

  val orders = TableQuery[OrdersTable]

  def findAllOrders(): Future[Seq[Order]] = db.run {
    orders.result
  }

  def order(order: Order): Future[Order] = db.run {
    (orders returning
      orders.map(_.id) into
      ((item, id) => item.copy(id = Some(id)))) += order
  }

}