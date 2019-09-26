package com.practice.two.wsclient

import org.json4s.DefaultFormats
import org.json4s.JsonAST.{JArray, JValue}
import org.json4s.native.JsonMethods
import scalaj.http.{Http, HttpResponse}

import scala.util.{Failure, Success, Try}

/**
  *
  * Esse programa realiza uma busca no wikipedia baseado no título e nos mostra os links referentes ao título
  *
  * Exercício:
  *   Usando Future e Recursividade, para cada link achado, faça outra busca até atingir o nível 5
  *
  */
object Example extends App {

  implicit val formats = DefaultFormats

  // Titulo a ser buscado
  val title = "Clube%20de%20Regatas%20do%20Flamengo"

  // Url da api do wikipedia
  val url = "https://en.wikipedia.org/w/api.php?action=query&titles="+title+"&prop=links&format=json"

  // Requisicao get
  val response: HttpResponse[String] = Http(url).asString

  if(response.isSuccess){

    // Converter a resposta em json
    val jsonParsed = JsonMethods.parse(response.body)

    Try {

      // recuperar o atributo 'pages' do JSON
      val pages = jsonParsed \ "query" \ "pages"

      // Recuperar o atributo 'links' do JSON
      pages
        .children
        .head // Primeiro item de 'pages'
        .extract[Map[String, JValue]] // Converter em mapa para buscar pelas chaves
        .filter(_._1.contains("links")) // Buscar o atributo 'link'
        .head. // Recuperar primeiro item dos 'links'
        _2. // Recuprar os valores do mapa de links
        asInstanceOf[JArray]
        .arr
        .map(link => {
          (link \ "title").values.toString
        })

    } match {
      case Success(links) =>

        println("Os links referentes ao título ["+title+"] são : ")
        links.foreach(println)

      case Failure(error) => println("Ocorreu um erro ao tentar recuperar os dados. ", error)
    }

  } else {
    println("Ocorreu o error ["+ response.code +"] na requisição.")
  }

}
