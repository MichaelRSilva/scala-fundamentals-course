import scala.collection.immutable.HashSet

/**
  * Execicios a serem executados via utilizando a IDE.
  * Tratamento de Conjuntos
  * Curso ministrado na Stefanini: Scala Fundamentals
  *
  * @author  Michael Silva
  * @since   2019-09-19
  */

object AppSets extends App {

  // Os cojuntos podem ser mutable ou nao
  // Conjuntos nao tem elementos repetidos

  // Criação e inicialização de conjuntos mutable
  val mutableSet: scala.collection.mutable.Set[Int] = scala.collection.mutable.Set(1,2,3)

  // Manipulacao dos conjuntos
  mutableSet.add(4)
  mutableSet.add(1)
  mutableSet.remove(2)

  mutableSet.foreach(println)



  // Criação e inicialização de conjuntos immutable
  val immutableSet: Set[Int] = HashSet(5,6,7)

  //immutableSet.add(1)
  //immutableSet.remove(2)

  immutableSet.foreach(println)


}
