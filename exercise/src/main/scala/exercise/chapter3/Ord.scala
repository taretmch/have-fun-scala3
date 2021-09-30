package exercise.chapter3

trait Ord[T]:
  def compare(x: T, y: T): Int
