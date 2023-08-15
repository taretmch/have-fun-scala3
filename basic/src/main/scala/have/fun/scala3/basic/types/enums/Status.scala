package have.fun.scala3.basic.types.enums

enum Status(val code: Short):
  case IS_IN_STOCK    extends Status(0)
  case IS_TODO        extends Status(1)
  case IS_IN_PROGRESS extends Status(2)
  case IS_DONE        extends Status(3)
  case IS_REJECTED    extends Status(-1)
end Status
                                                                            
object Status:
  def apply(code: Short): Status = Status.values.find(_.code == code) match
    case Some(state) => state
    case None        => throw new NoSuchElementException(code.toString)
end Status
