package have.fun.scala3.basic.types.struct

@main def runMain(): Unit =
  val person = SelectableImpl("name" -> "Taro", "age" -> 20).asInstanceOf[Person]
  println(person.name)
  println(person.age)
  //=> Taro
  //=> 20
