package com.practice.two.akka

import java.util.concurrent.atomic.AtomicLong

import akka.actor._
import com.practice.two.akka.PingProtocol.{PongMessage, StartMessage}
import com.practice.two.akka.PongProtocol.{PingMessage, StopMessage}


// Mensagens do ator Ping
object PingProtocol {
  case object StartMessage
  case object PongMessage
}

// Mensagens do ator POng
object PongProtocol {
  case object PingMessage
  case object StopMessage
}

class Ping(pong: ActorRef) extends Actor {

  val count: AtomicLong = new AtomicLong(0)

  def incrementAndPrint() {
    count.incrementAndGet()
    println(s"${self.path.name} Actor - Thread: ${Thread.currentThread.getId}")
  }

  def receive:PartialFunction[Any, Unit] = {

    case StartMessage =>

      incrementAndPrint()
      pong ! PingMessage

    case PongMessage =>

      incrementAndPrint()

      if (count.get() > 99) {

        sender ! StopMessage

        println(s"${self.path.name} Actor was stopped!")

        context.stop(self)

      } else {
        sender ! PingMessage
      }
  }
}

class Pong extends Actor {

  def receive:PartialFunction[Any, Unit] = {

    case PingMessage =>

      println(s"  ${self.path.name} Actor - Thread: ${Thread.currentThread.getId}")
      sender ! PongMessage

    case StopMessage =>

      println(s"${self.path.name} Actor was stopped! ")
      context.stop(self)
  }
}

object PingPongTest extends App {

  // Criação do sistema de atores
  val system = ActorSystem("ActorExampleSystem")

  // Criação dos atores
  val pong = system.actorOf(Props[Pong], name = "Pong")
  val ping = system.actorOf(Props(new Ping(pong)), name = "Ping")

  // Inicia a aplicação
  ping ! PingProtocol.StartMessage

  // Finalizar os atores para a aplicação finalizar de forma 'light'
  system.terminate()
}