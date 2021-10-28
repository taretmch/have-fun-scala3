package chapter4

import scala.concurrent.{ Future, ExecutionContext }

trait Functor[F[_]]:
  def fmap[A, B](f: A => B): F[A] => F[B]
  extension [A](fa: F[A])
    def map[B](f: A => B): F[B] = fmap(f)(fa)

object Functor:
  object instances extends FunctorInstances

trait FunctorInstances:
  given Functor[Option] with
    def fmap[A, B](f: A => B): Option[A] => Option[B] = _.map(f)

  given Functor[List] with
    def fmap[A, B](f: A => B): List[A] => List[B] = _.map(f)

  given (using ExecutionContext): Functor[Future] with
    def fmap[A, B](f: A => B): Future[A] => Future[B] = _.map(f)

  given [L]: Functor[Either[L, *]] with
    def fmap[A, B](f: A => B): Either[L, A] => Either[L, B] = _.map(f)
end FunctorInstances
