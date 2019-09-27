package io.michael.util

import com.typesafe.config.ConfigFactory

object ConfigUtil {

  private val config = ConfigFactory.load()

  def getHttpServerHost: Option[String] = Option(config.getString("http.host"))

  def getHttpServerPort: Option[Int] = Option(config.getInt("http.port"))

  def getRequestTimeout: Option[String] = Option(config.getString("akka.http.server.request-timeout"))

  def getTeamsCsvPath: Option[String] = Option(config.getString("appConfig.teamsCsvPath"))

}
