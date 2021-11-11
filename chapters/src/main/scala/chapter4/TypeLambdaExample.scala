package chapter4

object TypeLambdaExample:

  type Ex1 = [A, F[_]] =>> F[A]
  val ex1: Ex1[Int, List] = List(1, 2, 3)
  val ex12: [A] => A => Ex1[A, List] = [A] => (a: A) => List(a)
  val ex13: [A] => A => Ex1[A, Option] = [A] => (a: A) => Option(a)

  type FunctionK = [F[_], G[_]] =>> [A] =>> F[A] => G[A]
  def headOptionK[A]: FunctionK[List, Option][A] = _.headOption

  @main def runTypeLambdaExample() =
    assert(ex12(3) == List(3))
    assert(ex13("hoge") == Some("hoge"))
    assert(headOptionK(List(1, 2, 3, 4, 5)) == Some(1))
