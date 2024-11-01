ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "TDL"
  )

libraryDependencies += "org.apache.commons" % "commons-collections4" % "4.4"
