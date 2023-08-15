package have.fun.scala3.basic.simplifications

class ConstructorProxy(s: String):
  def this() = this("")
  def hello = println(s)
