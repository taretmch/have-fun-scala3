/** Copyright (c) 2020 The dotty-tasty-inspector-project contributors.
 *
 *  All rights reserved.
 */

package inspector

import scala.quoted._

@main def intersectionType =
  val tastyFile = "chapters/target/scala-3.0.1/classes/chapter1/IntersectionType$package.tasty"
  val tastyContents = Inspector.showCodeOf(tastyFile)
  println(tastyContents)
