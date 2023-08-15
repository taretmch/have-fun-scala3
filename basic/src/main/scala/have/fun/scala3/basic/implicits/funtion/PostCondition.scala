package have.fun.scala3.basic.implicits.function

object PostCondition:
  opaque type WrappedResult[T] = T

  def result[T](using r: WrappedResult[T]): T = r

  extension [T](x: T)
    def ensuring(condition: WrappedResult[T] ?=> Boolean): T =
      assert(condition(using x))
      x
