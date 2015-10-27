package database

import utils.DB

import scala.slick.driver.H2Driver.simple._

object UserCartDatabase {
  val userCart = TableQuery[UserCart]


  DB.connection.withSession { implicit session =>
    userCart.ddl.create
  }
}

case class CartEntity(userId: Int, productId: Int, quantity: Int, id: Option[Int] = None){

}

class UserCart(tag: Tag) extends Table[CartEntity](tag, "CART") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def userId = column[Int]("USER_ID", O.NotNull)
  def productId = column[Int]("PRODUCT_ID", O.NotNull)
  def quantity = column[Int]("QUANTITY", O.NotNull)
  def * = (userId, productId, quantity, id.?) <> (CartEntity.tupled, CartEntity.unapply)
  def customer = foreignKey("CUSTOMER_FK", userId, TableQuery[Users])(_.id)
  def product = foreignKey("PRODUCT_FK", productId, TableQuery[Products])(_.id)
  def userProduct = index("user_product", (userId, productId), unique = true)

}
