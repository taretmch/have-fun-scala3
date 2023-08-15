package have.fun.scala3.basic.simplifications

import java.io.IOException

object TryCatch:

  def f =
    try throw new IOException
    catch case ex: IOException => println("error")
