package have.fun.scala3.basic.implicits.`given`

trait Monoid[T]:
  def add(x: T, y: T): T
  def unit: T
  extension (x: T) def |+|(y: T): T = add(x, y)
