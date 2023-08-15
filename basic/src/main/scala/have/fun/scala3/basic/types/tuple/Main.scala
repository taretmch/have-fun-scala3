package have.fun.scala3.basic.types.tuple

@main def runMain(): Unit =
  println("Hello, Tuple!")
  val t1 = (1, "2", 3.0)

  val size = t1.size
  println(size)
  //=> 3

  val head = t1.head
  println(head)
  //=> 1

  val tail = t1.tail
  println(tail)
  //=> ("2", 3.0)

  val t2 = 4 *: Some(5) *: EmptyTuple
  println(t2)
  //=> (4, Some(5))

  val t3 = t1 ++ t2
  println(t3)
  //=> (1, "2", 3.0, 4, Some(5))

  // タプル t1 の要素を全て Long に変換する
  val t4 = t1.map[[X] =>> Long](
    [X] => (x: X) => x match
      case i: Int => i.toLong
      case s: String => s.toLong
      case d: Double => d.toLong
  )
  println(t4)
  //=> (1L, 2L, 3L)

  // タプル t3 の要素を Option[X] に変換する。ただし、Option[Option[X]] は Option[X] に変換される。
  val t5 = t3.map[[X] =>> Option[X] | X](
    [X] => (x: X) => x match
      case Some(_) => x
      case _ => Some(x)
  )
  println(t5)
  //=> (Some(1), Some("2"), Some(3.0), Some(4), Some(5))

  // [[*:]] には unapply が用意されているので、パターンマッチが可能
  (1, 2, 3, 4, 5) match
    case h *: tail => println(s"head: $h, tail: $tail")

  // Product の tuple への変換
  case class Person(name: String, age: Int)
  val p = Person("Alice", 20)
  val t6 = Tuple.fromProduct(p)
  println(p)
  println(t6)
  //=> Person(Alice,20)
  //=> (Alice, 20)
