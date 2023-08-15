package have.fun.scala3.basic.implicits.`given`

@main def runMain(): Unit =
  val list = List(Entity(3L), Entity(1L), Entity(4L), Entity(2L))
  println("Sort list of entities")
  println("Before: " + list.toString)
  println("Sorted: " + list.sorted.toString)
