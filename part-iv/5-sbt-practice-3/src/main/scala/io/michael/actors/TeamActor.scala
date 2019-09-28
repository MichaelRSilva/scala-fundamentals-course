package io.michael.actors

import akka.actor.{Actor, Props}
import akka.http.scaladsl.model.StatusCodes
import io.michael.messages.{ErrorResponse, TeamMessages}
import io.michael.messages.TeamMessages.{GetTeams, InsertTeam, Team}
import io.michael.wrappers.MongoWrapper
import org.mongodb.scala.{Completed, Observer}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object TeamActor {
  def props = Props(new TeamActor)
}

class TeamActor extends Actor {

  def receive: PartialFunction[Any, Unit] = {

    case GetTeams => {

      val actorToReturn = sender()

      MongoWrapper.teamCollection match {
        case Some(collection) =>
          collection.find().toFuture() onComplete  {
            case Success(teams) => actorToReturn ! TeamMessages.Teams(teams.toList)

            case Failure(_) => actorToReturn ! ErrorResponse(StatusCodes.InternalServerError, "An internal error has occurred. Contact the system administrator.")

          }
        case None => actorToReturn ! ErrorResponse(StatusCodes.InternalServerError, "An internal error has occurred. Contact the system administrator.")
      }
    }

    case InsertTeam(team) =>

      val actorToReturn = sender()

      MongoWrapper.teamCollection match {
        case Some(collection) =>
          collection.insertOne(team).subscribe(new Observer[Completed] {

            override def onNext(result: Completed): Unit = println("Inserted",result)

            override def onError(e: Throwable): Unit = println("Failed", e)

            override def onComplete(): Unit = println("Completed")
          })
        case None => actorToReturn ! ErrorResponse(StatusCodes.InternalServerError, "An internal error has occurred. Contact the system administrator.")
      }
  }


}
