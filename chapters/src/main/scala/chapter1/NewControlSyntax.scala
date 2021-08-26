package chapter1

import java.io.IOException

object NewControlSyntax:
  def ifFunc1(x: Int) =
    if x < 0 then
      "negative"
    else if x == 0 then
      "zero"
    else
      "positive"

  def ifFunc2(x: Int) =
    if x < 0 then -x else x

  def whileFunc1(x: Int, f: Int => Int) =
    var tmp = x
    while
      tmp >= 0
    do
      println(tmp)
      tmp = f(tmp)

  def forFunc1(xs: Seq[Int]) =
    for x <- xs if x > 0 yield x * x

  def forFunc2(xs: Seq[Int], ys: Seq[Int]) =
    for
      x <- xs
      y <- ys
    do
      println(x + y)

  def tryFunc1 =
    try throw new IOException
    catch case ex: IOException => println("error")
