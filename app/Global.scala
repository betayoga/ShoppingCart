import play.api.GlobalSettings
import play.api.mvc._
import utils.CartFilter
/**
 * Created by beta on 26/10/15.
 */
object Global extends WithFilters(CartFilter()) with GlobalSettings {
}