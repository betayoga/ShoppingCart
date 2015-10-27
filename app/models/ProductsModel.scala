package models

import database.ProductsDatabase._
import utils.DB

import scala.slick.driver.H2Driver.simple._
object ProductsModel {
  def getAll(limit: Option[Int]) = DB.connection.withSession { implicit session =>
    products.take(limit.getOrElse(10)).run
  }

  def getProductById(id: Int) = DB.connection.withSession { implicit session =>
    products.filter(_.id === id).run
  }
}