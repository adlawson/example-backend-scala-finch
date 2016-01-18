lazy val example = (project in file("."))
  .settings(
    name := "shunter-example-backend",
    organization := "com.github.shunterjs",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq(
      "-target:jvm-1.8",
      "-Xcheckinit",
      "-encoding", "UTF-8",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-Ywarn-unused-import"
    )
  )
