package controllers

import javax.inject._

import entities.Order
import play.api.libs.json.Json
import play.api.mvc._
import repository.OrderRepository

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               orderRepository: OrderRepository)
                              (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.order())
  }

  def getOrders = Action.async {
    orderRepository.findAllOrders().map(a => Ok(Json.toJson(a)))
  }

  def postOrder = Action.async(parse.json) { request =>
    request.body.validate[Order].
      map(o => orderRepository.order(o)
        map(a => Ok(Json.toJson(a)))).
      getOrElse(Future {
        BadRequest("Check your JSON")
      })
  }

}
