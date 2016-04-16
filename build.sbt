resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += Resolver.sonatypeRepo("releases")

name := "onion-shortest-path"
organization := "edu.allegheny"
version := "0.0.1"
scalaVersion := "2.11.8"

val scalatestVersion = "3.0.0-M12"
val spireVersion = "0.3.1"

libraryDependencies ++= Seq(
    "org.spire-math" % "algebra_2.11"     % spireVersion
  , "org.spire-math" % "algebra-std_2.11" % spireVersion
  , "org.scalactic"  %% "scalactic"       % scalatestVersion
  , "org.scalacheck" %% "scalacheck"      % "1.13.0"          % "test"
  , "org.scalatest"  %% "scalatest"       % scalatestVersion  % "test"
  )

