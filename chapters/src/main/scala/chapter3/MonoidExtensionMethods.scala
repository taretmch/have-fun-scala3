package chapter3

trait MonoidExtensionMethods:
  extension [T: Monoid](lhs: T)
    def |+|(rhs: T)(using ev: Monoid[T]): T = ev.add(lhs, rhs)
