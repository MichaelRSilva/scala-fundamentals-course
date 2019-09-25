package com.practice.ex.three

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Success, Failure}

object ConcurrencyAndFuture extends App {

  // Utilizando Map
  val fut1 = Future { Thread.sleep(2000); 1 + 21 }

  fut1.map(x => x + 1) onComplete  {
    case Success(r) => println("[Fut1] Success: " + r)
    case Failure(ex) => println("[Fut1] ", ex)
  }


  // Utilizando somente o onComplete com falha
  val fut2 = Future { Thread.sleep(2000); throw new Exception("A execucao falhou!")}

  fut2 onComplete  {
    case Success(r) => println("[Fut2] Success: " + r)
    case Failure(ex) => println("[Fut2] " , ex)
  }


  // Encadeamento
  val fut3 = for {
    x <- Future { Thread.sleep(2); 21 + 21 }
    y <- Future { Thread.sleep(1); 23 + 23 }
  } yield x + y


  fut3 onComplete  {
    case Success(r) => println("[Fut3] Success: " + r)
    case Failure(ex) => println("[Fut3] " , ex)
  }

  // Somente para a IDE segurar a execucao
  Thread.sleep(5000)


  // Esperar a execucao [Nao recomendado]
  val fut4 = Future { Thread.sleep(2000); 1 + 2}

  Await.result(fut4, 3.seconds)

  fut4 onComplete {
    case Success(r) => println("[Fut4] Success: " + r)
    case Failure(ex) => println("[Fut4] " , ex)
  }

}
