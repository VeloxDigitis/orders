package controllers

import java.util.Date
import javax.inject._

import entities.{FullOrder, Order}
import play.api.libs.json.Json
import play.api.mvc._
import repository.{ItemRepository, OrderRepository}

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               orderRepository: OrderRepository,
                               itemRepository: ItemRepository)
                              (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.order())
  }

  def listOrders = Action.async {
    orderRepository.findAllOrders().map(a => Ok(Json.toJson(a)))
  }

  def getOrder(id: Long) = Action.async {
    itemRepository.getOrderItems(id).map(a => Ok(Json.toJson(a)))
  }

  def postOrder = Action (parse.json) { request =>
    request.body.validate[FullOrder].map(
      fo => {
        orderRepository.order(Order(None, new Date().toString, fo.name, fo.age)).
          map(o => fo.items.foreach(i => itemRepository.addItem(i)))
        Ok
      }
    ).recoverTotal{
      e => BadRequest(Json.obj("status" -> "KO", "message" -> "Check your JSON"))
    }
  }


}
