package chapter4

object OpaqueTypeExample:
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

  @main def runCreateUser(): Unit =
    val user = User(User.Id(1L), User.Name("山田太朗"))
    assert(user == User(User.Id(1L), User.Name("山田太朗")))
    assert(user.id.toLong == 1L)
    assert(user.name.toString == "山田太朗")
