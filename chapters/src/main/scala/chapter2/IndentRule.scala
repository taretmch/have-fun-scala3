package chapter2

import java.time.LocalDateTime

/** Ref: Optional Braces, https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html */
object IndentRule:

  /** Sample 1: In a brace-delimited region, no statement is allowed to start
   *  to the left of the first statement
   *  after the opening brace that starts a new line.
   */
  def warning1(x: Int) =
    if (x < 0) {
      println(1)
      println(2)

    /** Compiler may emit a warning if you remove this comment out
    println(3)
    */
    }

  /** Sample 1: well-indented */
  def wellIndented1(x: Int) =
    if (x < 0)
      println(1)
      println(2)
      println(3)

  /** An <indent> is inserted at a line break, if
   *
   *  An indentation region can start at the current position in the source, and
   *  the first token on the next line has an indentation width strictly greater than the current indentation width
   *
   */
  def insertedIndent =
    // a) after the leading parameters of an extension
    extension (v: Seq[BigDecimal])  // a) <indent> will be inserted
      def avg: BigDecimal = v.sum / v.length

    // b) after a with in a given instance
    given orderingLocalDateTime: Ordering[LocalDateTime] with  // b) <indent> will be inserted
      def compare(x: LocalDateTime, y: LocalDateTime) =        // d) <indent> will be inserted
        x.compareTo(y)

    // c) after a “: at end of line” token
    object endOfLine:  // c) <indent> will be inserted
      val x = 1

    // e) after the closing ) of a condition in an old-style if or while
    if (endOfLine.x > 1)  // e) <indent> will be inserted
      println("hello")

    // f) after the closing ) or } of the enumerations of an old-style for loop without a do.
    for (x <- 1 to 5)  // f) <indent> will be inserted
      yield println(x)

    // f) after the closing ) or } of the enumerations of an old-style for loop without a do.
    for {
      x <- 1 to 5
    }  // f) <indent> will be inserted
      yield println(x)

  def insertedOutdent(x: Int) =
    if x < 0 then  // <indent>
      println("<outdent> will not be inserted in previous line.")  // <outdent>
    else  // <indent>
      println("<outdent> will be inserted in this line.")  // <outdent>

    // Define infix operator: Option[Int] + Option[Int]
    extension (lhs: Option[Int])  // <indent>
      infix def + (rhs: Option[Int]): Option[Int] =  // <indent>
        for  // <indent>
          l <- lhs
          r <- rhs  // <outdent>
        yield  // <indent>
          l + r   // <outdent>

    Option(3)
    + Option(4)



