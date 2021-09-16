package chapter2

object WildcardArguments:

  def f1: Int => Int = _ + 3
  def f2(list: List[_]): Option[_] =
    list.headOption
  
  trait Functor[F[_]] {

    def fmap[A, B](f: A => B): F[A] => F[B]
  }
