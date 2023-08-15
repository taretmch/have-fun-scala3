package have.fun.scala3.basic.simplifications

object For:
  def f1(xs: Seq[Int]) =
    for x <- xs if x > 0 yield x * x
                                             
  def f2(xs: Seq[Int], ys: Seq[Int]) =
    for
      x <- xs
      y <- ys
    do
      println(x + y)
