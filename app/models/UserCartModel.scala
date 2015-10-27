package models

import database.UserCartDatabase._
import database.{CartEntity, Products}
import utils.DB

import scala.slick.driver.H2Driver.simple._
object UserCartModel {
  def add(userId: Int, productId: Int) = DB.connection.withSession { implicit session =>
    (userCart returning userCart.map(_.id)) += CartEntity(userId, productId, 1)
  }

  def getUserProducts(userId: Int) = DB.connection.withSession { implicit session =>
    val query = for {
      userCart <- userCart if userCart.userId === userId
      product <- TableQuery[Products] if userCart.productId === product.id
    } yield (product.id, product.name, product.price, userCart.quantity)

    query.list.map { case (id, name, price, quantity) => Map("id" -> id, "name" -> name, "price" -> price, "quantity" -> quantity) }
  }

  def getUserProduct(userId: Int, productId: Int) = DB.connection.withSession { implicit session =>
    getByUserAndProduct(userId, productId).run
  }

  def updateProductQuantity(userId: Int, productId: Int, quantity: Int) = DB.connection.withSession { implicit session =>
    getByUserAndProduct(userId, productId).map(_.quantity).update(quantity)
  }

  def remove(userId: Int, productId: Int) = DB.connection.withSession { implicit session =>
    getByUserAndProduct(userId, productId).delete
  }

  def isProductInUserCart(productId: Int, userId: Int) = getUserProduct(userId, productId).nonEmpty

  private[this] def getByUserAndProduct(userId: Int, productId: Int) = DB.connection.withSession { implicit session =>
    userCart.filter(_.userId === userId).filter(_.productId === productId)
  }
}