package com.practice.ex.three

object FlatMapExample extends App {

  // O método flatMap transforma várias listas em uma lista
  val multipleList: List[List[Int]] = List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4), List(3, 4, 5), List(4, 5, 6))

  multipleList.flatMap(x => x).foreach(y => print(y + ","))

  // Ou simplesmente
  multipleList.flatten.foreach(y => print(y + ","))

  // É possível realizar alguma transformação
  val uniqueList: List[Int] = List(0, 1, 2, 3, 4, 5)

  def g(v:Int) = List(v-1, v, v+1)
  val listWithTransformation = uniqueList.flatMap(x => g(x))

  listWithTransformation.foreach(y => print(y + ","))

}
