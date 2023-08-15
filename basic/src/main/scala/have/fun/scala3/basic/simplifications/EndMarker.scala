/** End marker
  *
  * a) If the statement defines a member x then s must be the same identifier x.
  * b) If the statement defines a constructor then s must be this.
  * c) If the statement defines an anonymous given, then s must be given.
  * d) If the statement defines an anonymous extension, then s must be extension.
  * e) If the statement defines an anonymous class, then s must be new.
  * f) If the statement is a val definition binding a pattern, then s must be val.
  * g) If the statement is a package clause that refers to package p, then s must be the same identifier p.
  * h) If the statement is an if, while, for, try, or match statement, then s must be that same token.
  *
  * https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html
  */

// f) package clause
package have.fun.scala3.basic.simplifications:

  // a) member
  abstract class Member():
    // b) constructor
    def this(x: Int) =
      this()
      // h) if statement
      if x > 0 then
        // f) val definition
        val (a, b) =
          (x * 10, x + 10)
        end val
        // f) val definition
        var y =
          x
        end y
        // h) while statement
        while y > 0 do
          println(y)
          y -= 1
        end while
        // h) try statement
        try
          // h) match statement
          x match
            case 0 => println("0")
            case _ =>
          end match
        finally
          println("done")
        end try
      end if
    end this

    def f: String
  end Member

  // a) member
  object Member:
    // c) anonymous given
    given Member =
      // e) anonymous class
      new Member:
        // a) member
        def f = "!"
        end f
      end new
    end given
  end Member

  // d) anonymous extension
  extension (x: Member)
    def ff: String = x.f ++ x.f
  end extension

end simplifications
