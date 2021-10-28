package chapter4

import OpaqueTypeExample.User

object UnionTypeExample:
  object Group:
    opaque type Id = Long
    object Id:
      def apply(v: Long): Id = v
      extension (id: Id)
        def toLong: Long = id

  case class UserScore(uid: User.Id, scoreA: Int, scoreB: Int, scoreC: Int)
  case class GroupScore(gid: Group.Id, scoreA: Int, scoreB: Int, scoreC: Int, scoreD: Int, scoreE: Int)

  def calcAvg(score: UserScore | GroupScore): Double =
    score match
      case UserScore(_, a, b, c) => (a + b + c) / 3
      case GroupScore(_, a, b, c, d, e) => (a + b + c + d + e) / 5

