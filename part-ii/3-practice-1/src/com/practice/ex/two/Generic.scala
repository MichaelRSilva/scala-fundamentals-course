package com.practice.ex.two


// Criação de uma classe com um tipo genérico
trait Divide[T] {
  def divide(u: T, v: T): T
}

class IntDivide extends Divide[Int] {
  override def divide(u: Int, v: Int): Int = u / v
}

class DoubleDivide extends Divide[Double] {
  def divide(u : Double, v : Double) : Double = u / v
}

class Queue[T](list: List[T]) {
  def head = list.last
  def tail = new Queue(list.init)
  def enqueue(x: T) = new Queue(x :: list)
}

object Generic extends App {

  // Utilziando o intDivide
  val q = new IntDivide().divide(25, 5)
  println("IntDivide => " + q)

  // Utilziando o DoubleDivide
  val q2 = new DoubleDivide().divide(25, 3)
  println("DoubleDivide => " + q2)

  // Utilizando a Queue
  val q3 = new Queue[String](List("a", "b", "c"))
  println("q3.head => "+ q3.head)

}


// Exercício [1]: Perceba que essa Queue não está bem desenvolvida, pois não tem memória.
//  ou seja, ela retorna uma nova Queue para cada operação.
//
//  Desenvolva uma Queue que receba uma lista, um elemento ou nenhum elemento
//  As operações devem ficar persistidas na queue. Exeplo: ao realizar o enqueue e depois tentar recuprar ou realizar
//    a operação head, o novo item deve ser a resposta