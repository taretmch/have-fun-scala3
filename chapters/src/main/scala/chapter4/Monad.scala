package chapter4

import scala.concurrent.{ ExecutionContext, Future }

trait Monad[M[_]]:
  def flatMap[A, B](f: A => M[B])(ma: M[A]): M[B]
  def flatten[A](mma: M[M[A]]): M[A] = flatMap(identity[M[A]])(mma)
  def pure[A](a: A): M[A]
  extension [A, M[_]](ma: M[A])(using m: Monad[M])
    def flatMap[B](f: A => M[B]): M[B] = m.flatMap(f)(ma)

object Monad:
  object instances extends MonadInstances

trait MonadInstances:
  given Monad[Option] with
    def flatMap[A, B](f: A => Option[B])(ma: Option[A]): Option[B] = ma.flatMap(f)
    def pure[A](a: A): Option[A] = Some(a)

  given Monad[List] with
    def flatMap[A, B](f: A => List[B])(ma: List[A]): List[B] = ma.flatMap(f)
    def pure[A](a: A): List[A] = List(a)

  given (using ExecutionContext): Monad[Future] with
    def flatMap[A, B](f: A => Future[B])(ma: Future[A]): Future[B] = ma.flatMap(f)
    def pure[A](a: A): Future[A] = Future(a)

  given [L]: Monad[Either[L, *]] with
    def flatMap[A, B](f: A => Either[L, B])(ma: Either[L, A]): Either[L, B] = ma.flatMap(f)
    def pure[A](a: A): Either[L, A] = Right[L, A](a)
end MonadInstances
