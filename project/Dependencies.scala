import sbt._

object Dependencies {

  val Scala213      = "2.13.11"
  val Scala3        = "3.3.0"
  val ScalaVersions = Seq(Scala213, Scala3)

  val scalaTestVersion = "3.2.10"

  val scalatest: Seq[ModuleID] = Seq(
    "org.scalactic" %% "scalactic" % scalaTestVersion % Test,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  )

  val tastyInspector: ModuleID = "org.scala-lang" %% "scala3-tasty-inspector" % Scala3
}
