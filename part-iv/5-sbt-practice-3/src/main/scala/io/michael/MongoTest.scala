package io.michael

import io.michael.messages.TeamMessages.Team
import org.mongodb.scala._

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import org.mongodb.scala.bson.codecs.{DEFAULT_CODEC_REGISTRY, Macros}
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

import scala.concurrent.duration._
import scala.concurrent.Await

object MongoTest extends App{

  val SERVER = "ec2-18-231-183-151.sa-east-1.compute.amazonaws.com"
  val DATABASE="scala-course"
  val USER = "curso"
  val PASSWORD = "curso"

  val uri: String = s"mongodb://$USER:$PASSWORD@$SERVER/?authMechanism=SCRAM-SHA-1"
  val connection = MongoClient(uri)

  val teamCodecProvider = Macros.createCodecProvider[Team]()

  val codecRegistry = fromRegistries( fromProviders(teamCodecProvider), DEFAULT_CODEC_REGISTRY )

  val database = connection.getDatabase(DATABASE).withCodecRegistry(codecRegistry)

  val collection: MongoCollection[Team] = database.getCollection("michael")



  val team = Team("Flamengo", "RJ")

  collection.insertOne(team).subscribe(new Observer[Completed] {

    override def onNext(result: Completed): Unit = println("Inserted",result)

    override def onError(e: Throwable): Unit = println("Failed", e)

    override def onComplete(): Unit = println("Completed")
  })

  collection.find().toFuture() onComplete  {
    case Success(r) => println(r)
    case Failure(ex) => ex.printStackTrace()
  }


  Thread.sleep(10000)
}
