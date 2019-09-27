package io.michael

import java.util.concurrent.TimeUnit

import scala.concurrent.{ExecutionContextExecutor, Future}
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer
import akka.util.Timeout
import io.michael.messages.TeamMessages.Team
import org.mongodb.scala._
import org.mongodb.scala.bson.codecs.{DEFAULT_CODEC_REGISTRY, Macros}
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import io.michael.routes.Api
import io.michael.util.ConfigUtil
import org.mongodb.scala.MongoClient

import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object Main extends App with RequestTimeout {

  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  implicit val materializer: ActorMaterializer = ActorMaterializer.create(system)

  val api = new Api(system, requestTimeout()).routes

  val log = Logging(system.eventStream, "api")


  (ConfigUtil.getHttpServerHost, ConfigUtil.getHttpServerPort) match {
    case (Some(host), Some(port)) =>

      val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(api, host, port)

      Try {
        bindingFuture.map { serverBinding =>
          log.info(s"Api bound to ${serverBinding.localAddress}")
        }

      }
    case _ => Failure(new IllegalArgumentException("The HTTP para config is wrong."))
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

