package controllers

import models.UsersModel
import play.api.libs.json._
import play.api.libs.json.Json
import play.api.mvc.Controller
import database.UsersDatabase.users
import scala.util.Try
import models.UsersModel

/**
 * Created by beta on 27/10/15.
 */
object UserController  extends Controller{

  def add(userId: String){
    Try(UsersModel.add(userId))
    Ok(Json.obj("status" -> "OK"))
  }

  def getAll(limit: Option[Int]){
    var users=Try(UsersModel.getAll(limit))
    Ok(Json.toJson(UsersModel.getAll(limit)))
  }

  def getUserByName(name:String){
    var user=Try(UsersModel.getUserByToken(name))
    Ok(Json.toJson(user))
  }



}
