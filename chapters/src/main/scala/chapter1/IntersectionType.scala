package chapter1

trait Resettable:
  def reset(): Unit

trait Growable[T]:
  def add(t: T): Unit

def f(x: Resettable & Growable[String]) =
  x.reset()
  x.add("first")
