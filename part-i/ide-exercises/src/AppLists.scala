
/**
  * Execicios a serem executados via utilizando a IDE.
  * Tratamento de Listas
  * Curso ministrado na Stefanini: Scala Fundamentals
  *
  * @author  Michael Silva
  * @since   2019-09-19
  */

object AppLists extends App {

  // Criação e inicialização das listas
  // São imutáveis
  val listOne = List(1, 2, 3)
  val listTwo = List(3, 4)

  // Concatenação
  val listOneTwo = listOne ::: listTwo

  // Prepend
  val listPrepend = 5 :: listTwo

  // Iterando na lista
  //listPrepend.foreach(println)

  // Primeiro elemento
  //println(listPrepend.head)

  // Último elemento
  println(listPrepend.last)


  // [ EXERCÍCIO {1} ] Crie um objeto que inicialize 3 listas. Utilize pelo menos 10 métodos nas listas criadas.

  // [ EXERCÍCIO {2} ] Implemente as seguintes funções:

  // Retorna uma lista sem o item a ser removido
  def removeItem(list: List[String], toRemove: String): List[String] = {
    null
  }

  // Retorna uma item com todos os items que nao estao na intercessão de A com B
  def differenceBetween(listA: List[String], listB: List[String]): List[String] = {
    null
  }

  // Retorna o maior numero da lista
  def max(list: List[Int]): Int = {
    null
  }

  // Retorna o menor numero da lista
  def min(list: List[Int]): Int = {
    null
  }

  // Retornar a media da lista
  def mean(list: List[Int]): Int = {
    null
  }

}
