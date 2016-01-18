package com.github.shunterjs.backend

import com.twitter.finagle.Http
import com.twitter.util.Await
import io.circe.generic.auto._
import io.circe.parse._
import java.io.InputStream
import scala.util.Properties

object Main extends App {

  def getPort: String =
    Properties.envOrElse("PORT", "5000")

  def getImage(filename: String): Option[InputStream] =
    getResource(s"/images/$filename")

  def getVenues: Option[Seq[Venue]] = for {
    stream <- getResource("/data/venues.json")
    string <- streamToString(stream)
    venues <- decode[Seq[Venue]](string).toOption
  } yield venues

  getVenues match {
    case None => throw new RuntimeException("No venue data available")
    case Some(vs) => Await.ready {
      Http.serve(s":$getPort", endpoint.toService(vs, getImage))
    }
  }
}
