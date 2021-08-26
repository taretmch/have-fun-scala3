val scala3Version = "3.0.1"

ThisBuild / scalaVersion     := scala3Version
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.criceta"
ThisBuild / organizationName := "taretmch"

lazy val chapters = project.in(file("chapters"))
  .settings(name := "chapters")
  .settings(libraryDependencies ++= Seq(
    "com.novocode" % "junit-interface" % "0.11" % "test"
  ))

lazy val tastyInspector = project.in(file("tasty-inspector"))
  .settings(name := "tasty-inspector")
  .settings(libraryDependencies ++= Seq(
    "org.scala-lang" %% "scala3-tasty-inspector" % scala3Version,
    "com.novocode"    % "junit-interface"        % "0.11"         % "test"
  ))
  .dependsOn(chapters)

lazy val docs = project.in(file("docs"))
  .settings(name := "have-fun-scala3-docs")
  .enablePlugins(MdocPlugin)
  .settings(mdocIn  := file("docs/src/main"))
  .settings(mdocOut := file("docs/mdoc"))
