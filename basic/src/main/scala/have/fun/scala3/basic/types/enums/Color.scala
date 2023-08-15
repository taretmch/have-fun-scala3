package have.fun.scala3.basic.types.enums

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
