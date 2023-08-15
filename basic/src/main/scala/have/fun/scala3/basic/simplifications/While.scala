package have.fun.scala3.basic.simplifications

object While:
  def f(x: Int, f: Int => Int) =
    var tmp = x
    while
      tmp >= 0
    do
      println(tmp)
      tmp = f(tmp)
