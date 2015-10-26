package utils
import play.api.Logger
import play.api.mvc.{Filter, RequestHeader, Result}

/**
 * Created by beta on 26/10/15.
 */
case class CartFilter() extends Filter{
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent._

  def apply(f: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    Logger.debug("[cart] filtering request to add cart")
    f(request).map{_.withHeaders(
      "Access-Control-Allow-Origin" -> "*",
      "Access-Control-Allow-Headers" -> "Accept, Origin, Content-type, X-Json, X-Prototype-Version, X-Requested-With"
    )}
  }
}
