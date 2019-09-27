package io.michael.actors

import akka.actor.{Actor, Props}
import io.michael.messages.TeamMessages
import io.michael.messages.TeamMessages.{GetTeams, Team}
import io.michael.util.FileUtil

import scala.util.Success

object TeamActor {
  def props = Props(new TeamActor)
}

class TeamActor extends Actor {

  def receive: PartialFunction[Any, Unit] = {

    case GetTeams => {

      FileUtil.getTeamsCsv match {
        case Success(lines) =>

          val teams = lines.map(line => {
            val cols = line.split(";").map(_.trim)
            Team(cols(0), cols(1))
          })

          sender() ! TeamMessages.Teams(teams)
      }
    }
  }

}
