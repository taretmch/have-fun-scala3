package have.func.scala3.basic.types.intersection

trait Greeting:
  def hello: String

trait Bye:
  def bye: String

def say(x: Greeting & Bye): String =
  "Greeting: %s\nBye:      %s".format(x.hello, x.bye)

@main def runMain(): Unit =
  trait Lang extends Greeting, Bye

  val deutsch = new Lang:
    def hello: String = "Guten Tag"
    def bye:   String = "Auf Wiedersehen"

  val english = new Lang:
    def hello: String = "Hello"
    def bye:   String = "Bye"

  println(say(deutsch))
  println(say(english))
