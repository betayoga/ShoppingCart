package models

import models.Item.Item
import play.api.libs.json.Json

import scala.collection.mutable.ListBuffer
/**
 * Created by beta on 26/10/15.
 */
object Cart {
  case class CartModel(code: String, email: String,status: String,items:List[Item])

  implicit val cartWrites = Json.writes[CartModel]
  implicit val cartReads = Json.reads[CartModel]

  var carts = ListBuffer(new CartModel("initial","initial","initial",Item.items))

  def addCarts(cart: CartModel) = carts+=cart
  def updateCarts(cart: CartModel)={
    removeOne(cart)

    addCarts(cart)

  }

  private def removeOne(cart: CartModel) {
    carts= carts.filter(x => !x.code.equalsIgnoreCase(cart.code))

  }
}
