package answer.chapter3

trait Functor[F[_]]:
  def fmap[A, B](f: A => B): F[A] => F[B]
  extension [A](lhs: F[A])
    def map[B](f: A => B): F[B] = fmap(f)(lhs)
