package io.michael.routes

import akka.actor.{ActorRef, ActorSystem}
import akka.util.Timeout
import akka.pattern.ask
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import StatusCodes._

import io.michael.messages.TeamMessages.{GetTeam, GetTeams, Team, Teams}
import io.michael.actors.TeamActor
import io.michael.messages.EventMarshaller

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

class Api(system: ActorSystem, timeout: Timeout) extends RestRoutes {
  implicit val requestTimeout: Timeout = timeout
  implicit def executionContext: ExecutionContextExecutor = system.dispatcher

  def createTeamActor(): ActorRef = system.actorOf(TeamActor.props)
}


trait RestRoutes extends TeamApi with EventMarshaller {

  val service = "api"
  val version = "v1"

  protected val getAllTeamsRoute: Route = {
    pathPrefix(service / version / "teams") {
      get {
        // GET api/v1/teams
        pathEndOrSingleSlash {
          onSuccess(getTeams) { teams =>
            complete(OK, teams)
          }
        }
      }
    }
  }

  protected val getTeamRoute: Route = {
    pathPrefix(service / version / "teams" / Segment) { team =>
      get {
        // GET api/v1/teams/:team
        pathEndOrSingleSlash {
          onSuccess(getTeam(team)) { teams =>
            complete(OK, teams)
          }
        }
      }
    }
  }

  val routes: Route = getAllTeamsRoute ~ getTeamRoute

}

trait TeamApi {

  def createTeamActor(): ActorRef

  implicit def executionContext: ExecutionContext
  implicit def requestTimeout: Timeout

  lazy val teamActor: ActorRef = createTeamActor()

  def getTeams: Future[Teams] = teamActor.ask(GetTeams).mapTo[Teams]

  def getTeam(team: String): Future[Option[Team]] = teamActor.ask(GetTeam(team)).mapTo[Option[Team]]

}
