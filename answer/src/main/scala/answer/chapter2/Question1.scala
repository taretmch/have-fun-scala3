package answer.chapter2

/** Question: Constructor Proxy */
object Question1:

  def getInstanceOfA(): A =
    A()

  def getInstanceOfB(v: Int): B =
    B(v)

  def getInstanceOfC(s: String): C =
    C(s)

  def getInstanceOfC(): C =
    C()

class A:
  val value: Int = 5

class B(v: Int):
  val value: Int = v

class C(v: String):
  def this() = this("")
  def value: String = v
