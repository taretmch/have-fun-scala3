package answer.chapter2

/** Question: Rewrite in indent rule */
object Question2:

  def func1(x: Int): Int = {
    var y = x
    if (x > 0) {
      y = y * 2
      y = y * 3
    y = y * 6
    }
    y
  }

  def func2(x: Int): Int =
    var y = x
    if (x > 0) {
      y = y * 2
      y = y * 3
    }
    y = y * 6
    y
