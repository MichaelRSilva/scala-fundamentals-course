package io.michael.actors

import akka.actor.{Actor, Props}
import akka.http.scaladsl.model.StatusCodes
import io.michael.messages.{ErrorResponse, TeamMessages}
import io.michael.messages.TeamMessages.GetTeams
import io.michael.wrappers.MongoWrapper
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

object TeamActor {
  def props = Props(new TeamActor)
}

class TeamActor extends Actor {

  def receive: PartialFunction[Any, Unit] = {

    case GetTeams => {

      MongoWrapper.getTeamCollection match {
        case Some(collection) =>
          collection.find().toFuture() onComplete  {
            case Success(teams) =>

              sender() ! TeamMessages.Teams(teams.toList)

            case Failure(_) => sender() ! ErrorResponse(StatusCodes.InternalServerError, "An internal error has occurred. Contact the system administrator.")

          }
        case None => sender() ! ErrorResponse(StatusCodes.InternalServerError, "An internal error has occurred. Contact the system administrator.")
      }
    }
  }

}
