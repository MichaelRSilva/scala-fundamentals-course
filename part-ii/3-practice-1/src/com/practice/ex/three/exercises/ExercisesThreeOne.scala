package com.practice.ex.three.exercises

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ExercisesThreeOne extends App {

  /**
    * Crie um metodo A que retorne Future[Int]
    * Construa dois métodos a1 e a2 dentro de A, eles também devem retornar Future[Int]
    *
    * a) How is the expression translated to map and flatMap calls?
    * b) Are the two futures executed concurrently or one after the other?
    * c) In which thread does the call to println occur?
    */

  // a) This method answers the question
  def futureDemonstration(): Future[Int] = {
    // Note the use of def (instead of val) which ensures the futures will be started by f1.flatMap and f2.map, which
    // would be the same as using inline futures.
    def f1 = Future {
      Thread.sleep(1000)
      2
    }

    def f2 = Future {
      Thread.sleep(1000)
      40
    }

    // b) f1 is executed first and f2 after f1 has completed successfully
    // c) println call occurs in f2's thread
    f1.flatMap(n1 => f2.map(n2 => {
      println(n1 + n2)
      n1 + n2
    }))
  }


}
