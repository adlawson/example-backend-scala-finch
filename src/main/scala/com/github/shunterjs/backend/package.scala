package com.github.shunterjs

import java.io.InputStream
import scala.io.Source

package object backend {

  type Resources = String => Option[InputStream]

  def findBySlug(vs: Seq[Venue])(slug: String): Option[Venue] =
    vs.filter(_.slug == slug).headOption

  def getResource: Resources =
    (s: String) => Option(getClass.getResourceAsStream(s))

  def streamToString(stream: InputStream): Option[String] =
    Option(Source.fromInputStream(stream).getLines.mkString)
}
