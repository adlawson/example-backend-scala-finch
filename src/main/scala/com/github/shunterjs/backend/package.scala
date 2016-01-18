package com.github.shunterjs

import com.twitter.finagle.http.Status
import io.finch.{Extractor, Output}
import java.io.InputStream
import scala.io.Source
import scala.util.Random

package object backend {

  type Resources = String => Option[InputStream]

  // io.finch.Outputs.Found should be an empty response rather than a failure
  def Found(path: String): Output[Unit] = Output.Payload[Unit](
    Unit,
    Status.Found,
    Map("Location" -> path)
  )

  def filename: Extractor[String] = new Extractor("filename", { str =>
    if (str.endsWith(".jpg")) Some(str)
    else None
  })

  def findBySlug(vs: Seq[Venue])(slug: String): Option[Venue] =
    vs.filter(_.slug == slug).headOption

  def findByRand(vs: Seq[Venue]): Option[Venue] =
    if (vs.length == 0) None
    else Option(vs(Random.nextInt(vs.length)))

  def getResource: Resources =
    (s: String) => Option(getClass.getResourceAsStream(s))

  def streamToString(stream: InputStream): Option[String] =
    Option(Source.fromInputStream(stream).getLines.mkString)
}
