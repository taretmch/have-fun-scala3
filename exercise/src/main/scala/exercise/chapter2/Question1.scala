package exercise.chapter2

/** Question: Constructor Proxy */
object Question1:
  def getInstanceOfA(): A = ???
  def getInstanceOfB(v: Int): B = ???
  def getInstanceOfC(s: String): C = ???
  def getInstanceOfC(): C = ???

class A:
  val value: Int = 5

class B(v: Int):
  val value: Int = v

class C(v: String):
  def this() = this("")
  def value: String = v
