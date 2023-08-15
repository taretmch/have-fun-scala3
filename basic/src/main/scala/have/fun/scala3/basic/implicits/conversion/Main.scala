package have.fun.scala3.basic.implicits.conversion

import scala.language.implicitConversions

/** Types */
case class A(value: Int)
case class B(value: Long, log: String):
  def add(rhs: Long): B = copy(
    value = value + rhs,
    log   = log + " " + rhs.toString
  )
object B:
  def apply(v: Int): B = B(v.toLong, v.toString)
case class C(value: String):
  def add(rhs: String): C = copy(value + rhs)
object C:
  def apply(v: Int): C = C(v.toString)

/** Definition of implicit conversions */
object CustomConversions:
  given Conversion[Int,    A] = new A(_)
  given Conversion[Long,   B] = v => new B(v, v.toString)
  given Conversion[String, C] = new C(_)
  given Conversion[B,      C] = b => C(b.value.toString)

/** Main */
@main def runMain(): Unit =
  import CustomConversions.given

  val a: A = 3
  val b: B = 3L
  val c: C = "3"

  // Expected type
  assert(a == A(3))
  assert(b == B(3))
  assert(c == C(3))

  // Types have no defined member `value` and `log`
  assert(3.value == 3)
  assert(3L.log == "3")

  // Method's parameter has no match for `String`
  assert(B(3).add(4) == B(7, "3 4"))
  assert(B(3).add("4") == C("34"))
