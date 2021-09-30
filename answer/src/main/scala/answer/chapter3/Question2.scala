package answer.chapter3

/** Question: Using Ord */
object Question2:

  def max[T](list: List[T])(using ord: Ord[T]): T =
    list match {
      case Nil      => throw new Exception
      case x :: Nil => x
      case x :: xs  => max(x, max(xs))
    }
  
  def max[T](x: T, y: T)(using ord: Ord[T]): T =
    if ord.compare(x, y) < 0 then y else x
