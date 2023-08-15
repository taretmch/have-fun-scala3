package have.fun.scala3.basic.simplifications

object WildcardArguments:

  def f1: Int => Int = _ + 3
  def f2(list: List[?]): Option[?] =
    list.headOption
  
  trait Functor[F[_]] {

    def fmap[A, B](f: A => B): F[A] => F[B]
  }
