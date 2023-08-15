package have.fun.scala3.basic.types.opaque

case class User(id: User.Id, name: User.Name)
object User:
  opaque type Id   = Long
  opaque type Name = String
                                              
  object Id:
    def apply(v: Long): Id = v
    extension (id: Id)
      def toLong: Long = id
                                              
  object Name:
    def apply(v: String): Name = v
    extension (name: Name)
      def toString: String = name
