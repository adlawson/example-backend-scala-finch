lazy val example = (project in file("."))
  .settings(
    name := "shunter-example-backend",
    organization := "com.github.shunterjs",
    mainClass := Some("com.github.shunterjs.backend.Main"),
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
    ),
    libraryDependencies ++= Seq(
      "com.github.finagle" %% "finch-core" % "0.9.3",
      "com.github.finagle" %% "finch-circe" % "0.9.3",
      "io.circe" %% "circe-generic" % "0.2.1",
      "io.circe" %% "circe-parse" % "0.2.1"
    )
  )
