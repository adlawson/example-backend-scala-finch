package com.github.shunterjs.backend

import com.twitter.finagle.Service
import com.twitter.finagle.http
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

object endpoint {

  private val title = "Lunch Places"

  def toService(venues: Seq[Venue], images: Resources): Service[http.Request, http.Response] = (
    index(venues) :+:
    random(venues) :+:
    venue(venues)
  ).handle {
    case e: NoSuchElementException =>
      NotFound(new RuntimeException("Not found", e))
  }.toService

  def index(venues: Seq[Venue]): Endpoint[Response[VenueList]] = get(/) {
    val data = VenueList(title, venues)
    Ok(Response(Layout("home"), data))
  }

  def random(venues: Seq[Venue]): Endpoint[Unit] = get("random") {
    findByRand(venues) match {
      case None => throw new NoSuchElementException(s"""No venues available""")
      case Some(v) => Found(s"/${v.slug}")
    }
  }

  def venue(venues: Seq[Venue]): Endpoint[Response[VenueData]] = get(string) { slug: String =>
    findBySlug(venues)(slug) match {
      case None => throw new NoSuchElementException(s"""Venue with slug "$slug" doesn't exist""")
      case Some(v) =>
        val data = VenueData(s"${v.name} - $title", v)
        Ok(Response(Layout("venue"), data))
    }
  }
}
