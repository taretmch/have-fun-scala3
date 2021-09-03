/** Copyright (c) 2020 The dotty-tasty-inspector-project contributors.
 *
 *  All rights reserved.
 */

package inspector.chapter2

import inspector.Inspector
import scala.quoted._

@main def constructorProxy =
  val tastyFile = "chapters/target/scala-3.0.1/classes/chapter2/ConstructorProxy$package.tasty"
  val tastyContents = Inspector.showCodeOf(tastyFile)
  println(tastyContents)
