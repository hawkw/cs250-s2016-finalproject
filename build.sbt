resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"

name := "fingertree"
organization := "edu.allegheny"
version := "0.0.1"
scalaVersion := "2.12.0-M3"
val scalatestVersion = "3.0.0-M12"
libraryDependencies ++= Seq(
    "org.scalactic"  %% "scalactic"  % scalatestVersion
  , "org.scalacheck" %% "scalacheck" % "1.12.5"          % "test"
  , "org.scalatest"  %% "scalatest"  % scalatestVersion  % "test"
  )
