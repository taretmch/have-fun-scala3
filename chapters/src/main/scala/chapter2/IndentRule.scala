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
   * a) after the leading parameters of an extension
   * b) after a with in a given instance
   * c) after a “: at end of line” token
   * d) after one of the following tokens:
   *    =  =>  ?=>  <-  catch  do  else  finally  for
   *    if  match  return  then  throw  try  while  yield
   * e) after the closing ) of a condition in an old-style if or while
   * f) after the closing ) or } of the enumerations of an old-style for loop without a do.
   */
  def insertedIndent = // d) <indent> will be inserted after `=`
    extension (v: Seq[BigDecimal])  // a) <indent> will be inserted
      def avg: BigDecimal = v.sum / v.length

    given orderingLocalDateTime: Ordering[LocalDateTime] with  // b) <indent> will be inserted
      def compare(x: LocalDateTime, y: LocalDateTime) =        // d) <indent> will be inserted after `=`
        x.compareTo(y)

    object endOfLine:  // c) <indent> will be inserted
      val x = 1
    end endOfLine

    // d) <indent> will be inserted at end of line
    def hello: Int => Unit =
      x =>
        for
          y <-
            0 to 5
        do
          for
            z
              <-
                6 to 10
          yield
            if
              y % 2 == 0 then
                try
                  throw
                    new Exception("hoge error: " + y)
                catch
                  case e =>
                    println(e.getMessage)
                finally
                  println("z is divided by y")
            else if
              y % 3 == 0 then
                println("multiple number of three")
            else
              x match
                case _ if x % 2 == 0 =>
                  println("x is an even number")
                case _               =>
                  println("x is an odd number")
                

    if (endOfLine.x > 1)  // e) <indent> will be inserted
      println("hello")

    var y = 0
    while (y < 5)  // e) <indent> will be inserted
      println(y)
      y = y + 1

    for (x <- 1 to 5)  // f) <indent> will be inserted
      yield println(x)

    for {
      x <- 1 to 5
    }  // f) <indent> will be inserted
      yield println(x)
      

  /** An <outdent> is inserted at a line break, if
   *
   * a) the first token on the next line has an indentation width strictly less than the current indentation width, and
   * b) the last token on the previous line is not one of the following tokens which indicate that the previous statement continues:
   *    then  else  do  catch  finally  yield  match
   * c) if the first token on the next line is a leading infix operator.
   * then its indentation width is less then the current indentation width, and
   * it either matches a previous indentation width or is also less than the enclosing indentation width.
   *
   */
  def insertedOutdent(x: Int) =
    if x < 0 then  // <indent>
      println("parameter is negative.")  // <outdent>
    else  // <indent>
      println("parameter is positive.")  // <outdent>

    // Define infix operator: Option[Int] + Option[Int]
    extension (lhs: Option[Int])  // <indent>
      infix def + (rhs: Option[Int]): Option[Int] =  // <indent>
        for  // <indent>
          l <- lhs
          r <- rhs  // <outdent>
        yield  // <indent>
          l + r   // <outdent>
    
    if x > 0 then  // <indent>
        Some(x)  // <outdent>
      + Some(2)
      + Some(3)  // <outdent>
    else None
