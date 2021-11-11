package chapter4

object DependentFunctionTypeExample:
  trait Entry:
    type Key
    val key: Key
  
  object Hoge extends Entry:
    type Key = Int
    val key = 6
  
  object Fuga extends Entry:
     type Key = String
     val key = "aaaaaaaaaaaaaaaaaaaaaaaa"

  /** Dependent method (Scala 2 also supports) */
  def extractKey(e: Entry): e.Key = e.key

  /** Dependent function types
   *
   * We can use this function such as function value and
   * parameters of function.
   */
  val extractor: (e: Entry) => e.Key = extractKey

  def useExtractor(extractor: (e: Entry) => e.Key): Double =
    extractor(new Entry { type Key = Double; val key = 3.14 })
  
  @main def runDependentFunctionTypeExample(): Unit =
    assert(extractKey(Hoge) == 6)
    assert(extractKey(Fuga) == "aaaaaaaaaaaaaaaaaaaaaaaa")
    assert(extractor(Hoge) == 6)
    assert(extractor(Fuga) == "aaaaaaaaaaaaaaaaaaaaaaaa")
    assert(useExtractor(extractor) == 3.14)
