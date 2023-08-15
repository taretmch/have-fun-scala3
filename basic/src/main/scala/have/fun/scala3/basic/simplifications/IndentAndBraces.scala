package have.fun.scala3.basic.simplifications

object IndentAndBraces:

  def example(x: Int, y: Int) = {
    (
      x * (
        y + 1
      ) +
      (x +
      x)
    )
  }
