package controllers


import play.api.libs.json._
import play.api.mvc._
import models.Cart._
import models.UserCartModel
import database.UserCartDatabase.userCart
import scala.util.Try
import database.UserCart
/**
 * Created by beta on 26/10/15.
 */
object UserCartController extends Controller {

  def index(userId:Int){
    implicit val userFormat = Json.format[UserCart]
    implicit val cartWrites = Json.writes[UserCart]
    implicit val cartReads = Json.reads[UserCart]
    Ok(Json.toJson(userCart))

  }

  def getUserProducts(userId:Int){
    var result= (for {
      products <- Try(UserCartModel.getUserProducts(userId))
    } yield products)
    Ok(Json.toJson(result))

  }

  def getUserProduct(userId: Int, productId: Int){
    var result= (for {
      products <- Try(UserCartModel.getUserProduct(userId,productId))
    } yield products)
    Ok(Json.toJson(result))
  }

  def add(userId: Int, productId: Int){
    Try(UserCartModel.add(userId,productId))
    Ok(Json.obj("status" -> "OK"))
  }

}