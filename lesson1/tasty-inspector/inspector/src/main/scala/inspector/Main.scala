/** Copyright (c) 2020 The dotty-tasty-inspector-project contributors.
 *
 *  All rights reserved.
 */

package inspector

import scala.quoted._

@main def run =
  val tastyFile = "lib/target/scala-3.0.1/classes/lib/Hello.tasty"
  val tastyContents = Inspector.showCodeOf(tastyFile)
  println(tastyContents)
