package io.michael.messages

object TeamMessages {

  case class GetTeam(name: String)
  case class GetTeams()
  case class InsertTeam(team: Team)


  sealed trait TeamResponse extends Response

  case class Team(name: String, city: String) extends TeamResponse
  case class Teams(list: List[Team]) extends TeamResponse


}
