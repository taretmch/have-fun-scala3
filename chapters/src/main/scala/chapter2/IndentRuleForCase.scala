package chapter2

/** Special Treatment of case clauses */
object IndentRuleForCase:

  def f(x: Int) =
    // Indent region starts after this `match` because 
    // the following `case` appears at the indentation width that’s current for the match itself.
    x match
    case 1 => println("I")
    case 2 => println("II")
    case 3 => println("III")
    case 4 => println("IV")
    case 5 => println("V")
    case _ => println(x)
    println("indent region ended.")
