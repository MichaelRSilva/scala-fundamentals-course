package io.michael.messages

object TeamMessages {

  case class GetTeam(name: String)
  case class GetTeams()

  case class Team(name: String, city: String)
  case class Teams(list: List[Team])


}
