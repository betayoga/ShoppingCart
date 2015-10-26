package controllers


import play.api.libs.json._
import play.api.mvc._
import models.Cart._

/**
 * Created by beta on 26/10/15.
 */
object CartControllers extends Controller {

  def listCarts = Action {
    Ok(Json.toJson(carts))
  }

  def saveCart = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Cart]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toFlatJson(errors)))
      },
      cart => {
        addCarts(cart)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
  def updateCart=Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Cart]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toFlatJson(errors)))
      },
      cart => {
        updateCarts(cart)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
}