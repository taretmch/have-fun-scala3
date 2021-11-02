package example

import chapter3.Monoid
import chapter3.MonoidInstances._

object UseMonoid {

  def main(args: Array[String]) = {
    val monoid = summon[Monoid[Option[Int]]]
    assert(monoid.add(Option(3), Option(4)) == Option(7))
  }

  def summon[T](implicit instance: T): T = instance
}
