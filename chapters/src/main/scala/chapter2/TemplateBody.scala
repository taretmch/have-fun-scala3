package chapter2:

  object TemplateBody:
    trait A:
      def f: Int
    
    class C(x: Int) extends A:
      def f = x
    
    object O:
      def f = 3
    
    enum Color:
      case Red, Green, Blue
