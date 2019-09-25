import scala.collection.immutable.{HashMap, HashSet}

/**
  * Execicios a serem executados via utilizando a IDE.
  * Tratamento de Mapas
  * Curso ministrado na Stefanini: Scala Fundamentals
  *
  * @author  Michael Silva
  * @since   2019-09-19
  */

object AppMaps extends App {

  // Os maps podem ser mutable ou nao
  // Mapa nao tem chaves repetidas

  // Criação e inicialização de conjuntos mutable
  val mutableMap: scala.collection.mutable.Map[Int, String] = scala.collection.mutable.HashMap(1 -> "One", 2 -> "Two")

  // Manipulacao dos conjuntos
  mutableMap.put(3, "Three")
  mutableMap.put(1, "Onee")
  mutableMap.remove(2)

  mutableMap.keys.foreach(k => println("Key: " + k + " Value: " + mutableMap(k)))



  // Criação e inicialização de map immutable
  val immutableMap: Map[Int, String] = Map(1 -> "One", 2 -> "Two")
  immutableMap.keys .foreach(k => println("Key: " + k + " Value: " + immutableMap(k)))

}
