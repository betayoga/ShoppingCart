package models

import play.api.libs.json.Json

/**
 * Created by beta on 26/10/15.
 */
object Item {

  case class Item(itemCode: String, itemName: String,category: String,price:String)

  implicit val itemWrites = Json.writes[Item]
  implicit val itemReads = Json.reads[Item]

  var items = List(new Item("","","",""))

  def addItems(item: Item) = items = items ::: List(item)
}
