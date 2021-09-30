package answer.chapter3

/** Question: Ord instance */
object Question1:

  given Ord[Int] with
    def compare(x: Int, y: Int): Int =
      if      x < y then -1
      else if x > y then 1
      else               0

  given [T](using ord: Ord[T]): Ord[Option[T]] with
    def compare(xOpt: Option[T], yOpt: Option[T]) =
      (xOpt, yOpt) match
        case (None, None)       => 0
        case (None, Some(y))    => -1
        case (Some(x), None)    => 1
        case (Some(x), Some(y)) => ord.compare(x, y)
