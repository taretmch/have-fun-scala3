package chapter3

object ExtensionMethodsExample:
  case class Entity(id: Long, score: Int)

  extension [T](x: T)
    def <(y: T)(using ord: Ordering[T]): Boolean =
      ord.compare(x, y) == -1

  extension [T](xs: List[T])
    def sumBy[U: Numeric](f: T => U): U =
      xs.map(f).sum

  extension [T](xs: List[T])
    def avgBy(f: T => Int): Double =
      xs.map(f).sum / xs.length

  @main def runSumBy: Unit =
    val list = List(Entity(3L, 100), Entity(1L, 90), Entity(4L, 40), Entity(2L, 50))
    assert(list.sumBy(_.score) == 280)
    assert(list.avgBy(_.score) == 70.0)
