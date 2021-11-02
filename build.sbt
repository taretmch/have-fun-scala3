ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.criceta"
ThisBuild / organizationName := "taretmch"

val isDotty = Def.setting(
  CrossVersion.partialVersion(scalaVersion.value).exists(_._1 == 3)
)
val scala3Version = "3.1.0"
val kindProjectorVersion = "0.11.1"

val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.2.9",
    "org.scalatest" %% "scalatest" % "3.2.9" % "test"
  ),
  scalacOptions ++= Seq(
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:implicitConversions",
  ) ++ (if (isDotty.value) {
    Seq(
      "-unchecked",
      "-Ykind-projector"
    )
  } else {
    Seq(
      "-deprecation",
      "-Ytasty-reader",
      "-Xfatal-warnings"
    )
  })
)

lazy val chapters = project.in(file("chapters"))
  .settings(name := "chapters")
  .settings(scalaVersion := scala3Version)
  .settings(commonSettings: _*)

lazy val forScala2 = project.in(file("cross-version-modules/scala-2"))
  .settings(name := "for-scala-2")
  .settings(scalaVersion := "2.13.7")
  .settings(commonSettings: _*)
  .dependsOn(chapters)

lazy val tastyInspector = project.in(file("tasty-inspector"))
  .settings(name := "tasty-inspector")
  .settings(scalaVersion := scala3Version)
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "org.scala-lang" %% "scala3-tasty-inspector" % scala3Version,
  ))
  .dependsOn(chapters)

lazy val docs = project.in(file("docs"))
  .settings(name := "have-fun-scala3-docs")
  .settings(scalaVersion := scala3Version)
  .enablePlugins(MdocPlugin)
  .settings(mdocIn  := file("docs/src/main"))
  .settings(mdocOut := file("docs/mdoc"))

lazy val exercise = project.in(file("exercise"))
  .settings(name := "exercise")
  .settings(scalaVersion := scala3Version)
  .settings(commonSettings: _*)

lazy val answer = project.in(file("answer"))
  .settings(name := "answer")
  .settings(scalaVersion := scala3Version)
  .settings(commonSettings: _*)
