package models

import play.api.libs.json.Json
import models.Item.Item

/**
 * Created by beta on 26/10/15.
 */
object Cart {
  case class Cart(code: String, email: String,status: String,items:List[Item])

  implicit val cartWrites = Json.writes[Cart]
  implicit val cartReads = Json.reads[Cart]

  var carts = List(new Cart("initial","initial","initial",Item.items))

  def addCarts(cart: Cart) = carts = carts ::: List(cart)
  def updateCarts(cart: Cart): Unit ={
    removeOne(cart)

    addCarts(cart)

  }

  def removeOne(cart: Cart) {
    var i=0
    var remove=0
    for (c <- carts){
      if(c.code.equalsIgnoreCase(cart.code)){
        i -> remove
      }
      i+1
    }
    carts.dropRight(remove)
  }
}
