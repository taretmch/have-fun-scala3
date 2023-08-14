import Dependencies._
import BuildSettings._

ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.taretmch"
ThisBuild / organizationName := "taretmch"

// Project: Chapter programs
lazy val chapters = Scala3Project("chapters", "chapters")

// Project: CrossVersion modules for Scala 2
lazy val forScala2 = Scala2Project("for-scala-2", "cross-version-modules/scala-2")
  .dependsOn(chapters)

// Project: Tasy inspector samples
lazy val tastyInspectorSample = Scala3Project("tasty-inspector-sample", "tasty-inspector")
  .settings(libraryDependencies ++= Seq(tastyInspector))
  .dependsOn(chapters)

// Project: Document for Scala 3
lazy val docs = Scala3Project("have-fun-scala3-docs", "docs")
  .enablePlugins(MdocPlugin)
  .settings(mdocIn  := file("docs/src/main"))
  .settings(mdocOut := file("docs/mdoc"))

// Project: Exercise for chapters
lazy val exercise = Scala3Project("exercise", "exercise")

// Project: Answers for exercise
lazy val answer = Scala3Project("answer", "answer")

lazy val root = (project in file("."))
  .aggregate(chapters, forScala2, tastyInspectorSample, docs, exercise, answer)
  .settings(
    crossScalaVersions := Nil,
    publish / skip := true
  )

Global / onChangedBuildSource := ReloadOnSourceChanges
