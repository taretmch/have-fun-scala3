/** Copyright (c) 2020 The dotty-tasty-inspector-project contributors.
 *
 *  All rights reserved.
 */

package inspector.chapter4

import inspector.Inspector
import scala.quoted._

@main def enumExample =
  val tastyFile = "chapters/target/scala-3.1.0/classes/chapter4/EnumExample.tasty"
  val tastyContents = Inspector.showCodeOf(tastyFile)
  println(tastyContents)

