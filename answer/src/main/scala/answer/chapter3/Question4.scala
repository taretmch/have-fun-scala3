package answer.chapter3

import scala.concurrent.{ Future, ExecutionContext }
import scala.language.implicitConversions

/** Question: Functor instances */
object Question4:

  given Functor[Option] with
    def fmap[A, B](f: A => B): Option[A] => Option[B] = _.map(f)

  given Functor[List] with
    def fmap[A, B](f: A => B): List[A] => List[B] = _.map(f)

  given (using ExecutionContext): Functor[Future] with
    def fmap[A, B](f: A => B): Future[A] => Future[B] = _.map(f)
