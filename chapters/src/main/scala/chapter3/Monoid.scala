package chapter3

trait Monoid[T]:
  extension (x: T) def add(y: T): T
  extension (x: T) def |+|(y: T): T = x.add(y)
  def unit: T
