package chapter2

class ConstructorProxy(s: String):
  def this() = this("")
  def hello = println(s)

@main def hello(): Unit =
  val c1 = ConstructorProxy("abc")
  val c2 = ConstructorProxy()
  c1.hello
  c2.hello
