package chapter3

object GivenExample:
  case class Entity(id: Long)

  given Ordering[Entity] with
    def compare(x: Entity, y: Entity): Int =
      summon[Ordering[Long]].compare(x.id, y.id)

  @main def runSort: Unit =
    val list = List(Entity(3L), Entity(1L), Entity(4L), Entity(2L))
    println("Sort list of entities")
    println("Before: " + list.toString)
    println("Sorted: " + list.sorted.toString)
