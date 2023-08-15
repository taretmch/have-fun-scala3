package have.fun.scala3.basic.simplifications

object If:

  def f1(x: Int) =
    if x < 0 then
      "negative"
    else if x == 0 then
      "zero"
    else
      "positive"
                            
  def f2(x: Int) =
    if x < 0 then -x else x
