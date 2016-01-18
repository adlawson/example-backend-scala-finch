package com.github.shunterjs.backend

sealed trait Data {
  def title: String
}

case class Layout(template: String)

case class Location(address: Seq[String], lat: String, lng: String)

case class Response[T <: Data](layout: Layout, data: T)

case class Venue(name: String, slug: String, url: String, location: Location)

case class VenueData(title: String, venue: Venue) extends Data

case class VenueList(title: String, venues: Seq[Venue]) extends Data
