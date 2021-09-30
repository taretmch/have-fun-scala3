package chapter3

import scala.concurrent.{ ExecutionContext, Future }

object UsingExample extends MonoidInstances:

  def sum[T: Monoid](list: List[T])(using ev: Monoid[T]) =
    list.foldLeft(ev.unit)(ev.add)

  def sumInt: Int =
    sum(List(1, 2, 3, 4, 5))

  def sumString: String =
    sum(List("Hello", ",", " ", "world", "!"))

  def sumListInt: List[Int] =
    sum(List(List(1, 2, 3), List(4, 5)))

  def sumListString: List[String] =
    sum(List(List("Hello", ",", " "), List("world", "!")))

  def sumSumListString: String =
    sum(sumListString)

  def sumOptionInt: Option[Int] =
    sum(List(Option(1), Option(2), None, Option(3), Option(4), Option(5)))

  @main def run: Unit =
    import scala.concurrent.ExecutionContext.Implicits.global
    usingExecutionContext

  def usingExecutionContext(using ExecutionContext) =
    Future {
      (1 to 10).foreach { i =>
        Thread.sleep(1000)
        println(i)
      }
    }
