package chapter3

trait Monoid[A]:
  def add(x: A, y: A): A
  def unit: A
