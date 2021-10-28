package chapter4

import chapter3.Monoid

object IntersectionTypeExample:
  trait S[F[_]] extends Functor[F], Monad[F]
  given [F[_]](using functor: Functor[F], monad: Monad[F]): S[F] with
    def fmap[A, B](f: A => B): F[A] => F[B] = functor.fmap(f)
    def flatMap[A, B](f: A => F[B])(ma: F[A]): F[B] = monad.flatMap(f)(ma)
    def pure[A](a: A): F[A] = monad.pure(a)

  def f[F[_]: S, A](fa: F[A]): F[Int] =
    def toInt(a: A): Int = 3
    def toFInt(a: A): F[Int] = summon[Monad[F]].pure(3)
    fa.map(toInt)
    fa.flatMap(toFInt)

  type T[F[_]] = Functor[F] & Monad[F]
  def g[F[_]: T, A](fa: F[A]): F[Int] =
    def toInt(a: A): Int = 3
    def toFInt(a: A): F[Int] = summon[Monad[F]].pure(3)
    fa.map(toInt)
    fa.flatMap(toFInt)

  @main def runOptionMap(): Unit =
    import Functor.instances.given
    import Monad.instances.given
    assert(f(Option(4)) == Option(3))

  trait Greeting:
    def hello: String
  trait Bye:
    def bye: String

  def say(x: Greeting & Bye): String=
    "Greeting: %s\nBye:      %s".format(x.hello, x.bye)

  @main def runSayHello(): Unit =
    trait C extends Greeting, Bye
    val c = new C:
      def hello: String = "Guten Tag"
      def bye:   String = "Auf Wiedersehen"
    println(say(c))
