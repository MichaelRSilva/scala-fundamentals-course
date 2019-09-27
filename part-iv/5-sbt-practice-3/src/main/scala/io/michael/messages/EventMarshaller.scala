package io.michael.messages

import play.api.libs.json._
import de.heikoseeberger.akkahttpplayjson._
import io.michael.messages.TeamMessages.{Team, Teams}

case class Error(code: Int, message: String)

trait EventMarshaller extends PlayJsonSupport {

  implicit val errorFormat: OFormat[Error] = Json.format[Error]
  implicit val teamFormat: OFormat[Team]   = Json.format[Team]
  implicit val teamsFormat: OFormat[Teams] = Json.format[Teams]

}
