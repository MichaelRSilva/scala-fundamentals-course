
/**
  * Execicios a serem executados via utilizando a IDE.
  * Tratamento de Arrays
  * Curso ministrado na Stefanini: Scala Fundamentals
  *
  * @author  Michael Silva
  * @since   2019-09-19
  */

object AppArrays extends App {

  // Criação do array de String com tamanho máximo de 3
  val arr = new Array[String](3)

  // Inicialização do array
  arr(0) = "Hello"
  arr(1) = ", "
  arr(2) = "world!\n"

  //ArrayIndexOutOfBoundsException
  //arr(3) = "!!!!\n"

  // Iteração no array com FOR e TO
  //for (i <- 0 to 2) print(arr(i))

  // Iteração no array com FOR e Until
  //for (i <- 0 until 2) print(arr(i))

  // Atualizar um array
  //arr.update(1, " <> ")
  //for (i <- 0 until 2) print(arr(i))


  // Criação e inicialização
  //val names = Array("zero", "one", "two")
  //for (i <- 0 to 2) print(names(i))

}
