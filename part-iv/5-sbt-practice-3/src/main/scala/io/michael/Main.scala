package io.michael

import java.util.concurrent.TimeUnit

import scala.concurrent.{ExecutionContextExecutor, Future}
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer
import akka.util.Timeout
import io.michael.routes.Api
import io.michael.util.ConfigUtil

import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object Main extends App with RequestTimeout {

  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  implicit val materializer: ActorMaterializer = ActorMaterializer.create(system)

  val api = new Api(system, requestTimeout()).routes

  val log = Logging(system.eventStream, "api")

  (ConfigUtil.getHttpServerHost, Some(9090)) match {
    case(Some(host), Some(port)) =>

      val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(api, "0.0.0.0", port)

      Try {

        bindingFuture.map { serverBinding =>
          log.info(s"Api bound to ${serverBinding.localAddress}")
        }

      } match {
        case Success(_) => log.info(s"The server is up.")
        case Failure(ex) =>
          log.error(ex, s"Failed to bind to $host:$port !")
          system.terminate()
      }
  }
}

trait RequestTimeout {

  def requestTimeout(): Timeout = {
    ConfigUtil.getRequestTimeout match {
      case Some(reqTimeout) =>

        val d = Duration(reqTimeout)
        FiniteDuration(d.length, d.unit)

      case None => FiniteDuration(3000, TimeUnit.MILLISECONDS)
    }
  }
}

