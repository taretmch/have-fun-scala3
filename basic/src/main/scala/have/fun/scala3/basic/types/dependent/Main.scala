package have.fun.scala3.basic.types.dependent

trait Entry:
  type Value
  val value: Value

class IntEntry(val value: Int) extends Entry:
  type Value = Int

class StringEntry(val value: String) extends Entry:
   type Value = String

object Entry:
  def getValue(e: Entry): e.Value = e.value
  val valueGetter: (e: Entry) => e.Value = _.value

@main def runMain(): Unit =

  val intEntry = IntEntry(6)
  val stringEntry = StringEntry("aaaaaaaaaaaaaaaaaaaaaaaa")
  val doubleEntry = new Entry { type Value = Double; val value = 3.14 }
  
  assert(Entry.getValue(intEntry) == 6)
  assert(Entry.getValue(stringEntry) == "aaaaaaaaaaaaaaaaaaaaaaaa")
  assert(Entry.getValue(doubleEntry) == 3.14)
  assert(Entry.valueGetter(intEntry) == 6)
  assert(Entry.valueGetter(stringEntry) == "aaaaaaaaaaaaaaaaaaaaaaaa")
  assert(Entry.valueGetter(doubleEntry) == 3.14)
