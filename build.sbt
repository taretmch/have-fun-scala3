import Dependencies._
import BuildSettings._

ThisBuild / organization     := "com.github.taretmch"
ThisBuild / organizationName := "taretmch"

lazy val basic = Scala3Project("basic", "basic")

lazy val root = Scala3Project("root", ".")
  .aggregate(basic, intermediate, advanced)
  .dependsOn(basic, intermediate, advanced)

Global / onChangedBuildSource := ReloadOnSourceChanges
