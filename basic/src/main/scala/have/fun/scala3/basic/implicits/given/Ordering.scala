package have.fun.scala3.basic.implicits.`given`

case class Entity(id: Long)

given Ordering[Entity] with
  def compare(x: Entity, y: Entity): Int =
    summon[Ordering[Long]].compare(x.id, y.id)
