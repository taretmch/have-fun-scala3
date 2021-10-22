package chapter4

object EnumExample:

  enum Color:
    case Red, Green, Blue

  enum Color2(val rgb: Int):
    case Red   extends Color2(0xFF0000)
    case Green extends Color2(0x00FF00)
    case Blue  extends Color2(0x0000FF)

  enum Color3(rgb: Int):
    def rgbHex: String = rgb.toHexString
    case Red   extends Color3(0xFF0000)
    case Green extends Color3(0x00FF00)
    case Blue  extends Color3(0x0000FF)
  end Color3

  object Task:
    enum Status(val code: Short):
      case IS_IN_STOCK    extends Status(0)
      case IS_TODO        extends Status(1)
      case IS_IN_PROGRESS extends Status(2)
      case IS_DONE        extends Status(3)
      case IS_REJECTED    extends Status(-1)
    end Status

    /** Companion object of Enum Status */
    object Status:
      def apply(code: Short): Status = Status.values.find(_.code == code) match
        case Some(state) => state
        case None        => throw new NoSuchElementException(code.toString)
    end Status
  end Task
