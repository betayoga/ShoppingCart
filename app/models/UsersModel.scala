package models

import database.UserEntity
import database.UsersDatabase._
import utils.DB

import scala.slick.driver.H2Driver.simple._

object UsersModel {
  def add(token: String) = DB.connection.withSession { implicit session =>
    users += UserEntity(token)
  }

  def getAll(limit: Option[Int]) = DB.connection.withSession { implicit session =>
    users.take(limit.getOrElse(10)).run
    print(users)
  }

  def getUserByToken(token: String) = DB.connection.withSession { implicit session =>
    users.filter(_.token === token).run
  }
}