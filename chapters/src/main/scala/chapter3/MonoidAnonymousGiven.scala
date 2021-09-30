package chapter3

trait MonoidAnounymousGiven:
  given Monoid[Int] with
    extension (x: Int) def add(y: Int): Int = x + y
    def unit: Int = 0

  given Monoid[String] with
    extension (x: String) def add(y: String): String = x + y
    def unit: String = ""

  given [A](using m: Monoid[A]): Monoid[Option[A]] with
    extension (x: Option[A]) def add(y: Option[A]): Option[A] =
      (x, y) match
        case (Some(x1), Some(y1)) => Some(x1.add(y1))
        case (Some(x1), None)     => Some(x1)
        case (None,     Some(y1)) => Some(y1)
        case _                    => None
    def unit: Option[A] = None

  given [A]: Monoid[List[A]] with
    extension (x: List[A]) def add(y: List[A]): List[A] = x ++ y
    def unit: List[A] = List()
