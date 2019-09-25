package com.practice.ex.one

import scala.collection.immutable.HashMap
import scala.util.{Success, Try, Failure}


trait Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object CaseClassAndMatch extends App {

  // Exemplo de uso da case class
  val v = Var("x")
  val op = BinOp("+", Number(1), v)

  // Acessando os fiels
  println("v.name => " + v.name)
  println("op.left => " + op.left)

  // O Scala implementa um toString naturalmente e legível
  println(op)

  // "==" em Scala sempre é usado como o equals (Java)
  println("op.right == Var(x) => " + (op.right == Var("x")))

  // Pattern Matching
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e // Double negation
    case BinOp("+", e, Number(0)) => e // Adding zero
    case BinOp("*", e, Number(1)) => e // Multiplying by one
    case _ => expr
  }

  println(simplifyTop(simplifyTop(UnOp("-", UnOp("-", Var("x"))))))

  // Utilizando  Try
  val map: Map[String, Int] = HashMap("one" -> 1, "two" -> 2)

  Try {

    if(map.contains("four")) println("Has 4.")
    else throw new Exception("Element not found")

  } match {

    case Success(_) => println("All right!")

    case Failure(e) => e.printStackTrace()

  }


  // Tipo Option
  // Tem 2 opções: Some(_) our None
  val capitals =  Map("France" -> "Paris", "Japan" -> "Tokyo")

  println(capitals get "Japan")
  println(capitals get "Brazil")

  capitals get "Brazil" match {
    case Some(capital) => println("The capital of Brazil is: " + capital)
    case None => println("Brazil's capital did not found.")
  }

}


// Exercício [1]:
//  Faça uma função que retorne o código RGB (objeto) referente as seguintes cores: azul, vermelho, preto, branco, verde

// Exercício [2]:
//  Faça uma função que receba uma tupla  (String, String) como argumento, o primeiro item da tupla é um animal, o segundo é uma comida.
//  O retorno seguirá os seguintes critérios:
//    Se o animal for um cachorro e o alimento for carne, retornará 'cachorro comendo carne'
//    Se o animal for um gato, então retornará 'dormindo'
//    Se a comida for espinafre, então retornará 'popeye'
//    Se a comida for nozes, então retornará esquilo
//    Se o animal for esquilo, retornará nozes
//

// Exercício [3]:
//  Faça uma função uma calculadora usando pattern matching.
//    A calculadora deverá realizar as seguintes operações:
//      soma, subtração, multiplicação, divisão, raiz quadrada, log na base 10