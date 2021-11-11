package chapter4

object TypeLambdaExample:

  type Ex1 = [A, F[_]] =>> F[A]
  val ex1: Ex1[Int, List] = List(1, 2, 3)
  val ex12: [A] => A => Ex1[A, List] = [A] => (a: A) => List(a)
  val ex13: [A] => A => Ex1[A, Option] = [A] => (a: A) => Option(a)

  type FunctionK = [A, F[_], G[_]] =>> F[A] => G[A]
  def headOptionK[A]: FunctionK[A, List, Option] = (xs: List[A]) => xs.headOption

  @main def runTypeLambdaExample() =
    assert(ex12[Int](3) == List(3))
    assert(ex13[String]("hoge") == Some("hoge"))
    assert(headOptionK[Int](List(1, 2, 3, 4, 5)) == Some(1))
