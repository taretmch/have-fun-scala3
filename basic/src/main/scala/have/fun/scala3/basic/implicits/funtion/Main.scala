package have.fun.scala3.basic.implicits.function

import scala.util.Try

object Main:
  @main def runHtml(): Unit =
    given Tag.Indent = Tag.Indent(0)
    val template = html {
      div {
        p("Hello")
        img("/assets/images/hoge.jpg")
      }
    }
    println(template.toHtmlString)

  @main def runPostCondition(): Unit =
    import PostCondition.*

    /** Should be 6 */
    println(Try(List(1, 2, 3).sum.ensuring(result == 6)))

    /** Should be error */
    println(Try(List(1, 2).sum.ensuring(result == 6)))
