name := """orders"""
organization := "me.veloxdigitis"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.1"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1"
libraryDependencies += "com.h2database" % "h2" % "1.4.192"