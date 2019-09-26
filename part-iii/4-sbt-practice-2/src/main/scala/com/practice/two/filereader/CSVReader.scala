package com.practice.two.filereader


object CSVReader extends App {

  // Caminho do arquivo a ser lido
  val bufferedSource = io.Source.fromFile("/Users/michaelsilva/Desktop/teams.csv")

  // Percorrendo cada linha do arquivo
  for (line <- bufferedSource.getLines) {

    // Recuperando as colunas do arquivo
    val cols = line.split(";").map(_.trim)
    println(s"${cols(0)} [${cols(1)}]")
  }

  bufferedSource.close
}

// Exercício [1]
//  Criar uma classe que tenha cada coluna do csv como atributo
//    Essa classe deverá ter os seguintes métodos:
//      Metodo que retorne o ano de fundacao do time
//      Metodo que retorne quantos titulos o time ganhou em media desde a fundacao
//      Metodo que retorne quantos torcedores o time conquistou em media desde a fundacao
//      Metodo que retorne a sigla do time com 3 letras

// Exercício [2]
//  Busque, na internet, uma biblioteca que leia um csv e utilize-a.

// Exercício [3]
//  Cria uma classe que tenha os times (composica)
//    Essa classe deverá ter os seguintes métodos:
//      Método que retorne o time mais antigo
//      Método que retorne o time mais vitorioso
//      Método que retorne o time com mais torcedores
//      Método que retorne o time mais vitorioso de cada estado

