package chapter2

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
