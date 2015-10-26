package model
import play.api.libs.json.Json
/**
 * Created by beta on 26/10/15.
 */
case class Cart (status:String,code:String,email:String)

object Cart{
  var list: List[Cart] = {
    List(
      Cart(
        "janda",
        "001",
        "betayoga@gmail.com"

      ),
      Cart(
        "janda",
        "002",
        "betayoga@gmail.com"

      )
    )
  }

  def save(carts: Cart) = {
    list = list ::: List(carts)
  }

  implicit val cartFormat = Json.format[Cart]


}
