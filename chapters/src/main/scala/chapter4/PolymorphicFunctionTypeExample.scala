package chapter4

object PolymorphicFunctionTypeExample:
  def headOptionK[A](xs: List[A]): Option[A] = xs.headOption
  val headOptionFunK: [A] => List[A] => Option[A] = [A] => (xs: List[A]) => headOptionK[A](xs)

  case class Const[C, +A](v: C)
  def lengthK[A](xs: List[A]): Const[Int, A] = Const(xs.length)
  val lengthFunK: [A] => List[A] => Const[Int, A] = [A] => (xs: List[A]) => lengthK[A](xs)

  @main def runPolymorphicFunctionTypeExample() =
    assert(headOptionK[Int](List(1, 2, 3)) == Some(1))
    assert(headOptionFunK[Int](List(1, 2, 3)) == Some(1))
    assert(lengthK[Int](List(1, 2, 3)) == Const(3))
    assert(lengthFunK[Int](List(1, 2, 3)) == Const(3))
