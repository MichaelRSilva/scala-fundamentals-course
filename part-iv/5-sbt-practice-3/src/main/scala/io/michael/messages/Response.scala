package io.michael.messages

import akka.http.scaladsl.model.StatusCode

trait Response

case class ErrorResponse(code: StatusCode, message: String) extends Response