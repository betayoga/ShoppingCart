package database

import play.api.libs.json.{JsValue, Json, Writes}
import utils.DB

import scala.slick.driver.H2Driver.simple._
object UsersDatabase {

  implicit val implicitFooWrites = new Writes[UserEntity] {
    def writes(foo: UserEntity): JsValue = {
      Json.obj(
        "id" -> foo.id,
        "token" -> foo.token
      )
    }
  }

  val users = TableQuery[Users]
  implicit val userReads = Json.reads[UserEntity]
  DB.connection.withSession { implicit session =>
    users.ddl.create
  }
}

case class UserEntity(token: String, id: Option[Int] = None)

class Users(tag: Tag) extends Table[UserEntity](tag, "USERS") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def token = column[String]("TOKEN", O.NotNull)
  def * = (token, id.?) <> (UserEntity.tupled, UserEntity.unapply)
}