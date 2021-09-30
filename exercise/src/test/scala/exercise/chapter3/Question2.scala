package exercise.chapter3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import Question1.given
import Question2.*

class Question2Spec extends AnyFlatSpec with Matchers {

  "max[Int]" should "Ord[Int] にしたがって、最大の数を返す" in {
    assert(max(List(1, 2, 5, 4, 3)) == 5)
    assert(max(List(3)) == 3)
    assert(max(List(4, 4)) == 4)
  }

  "max[Option[Int]]" should "Ord[Option[Int]] にしたがって、最大の数を返す" in {
    assert(max(List(Some(1), None)) == Some(1))
    assert(max[Option[Int]](List(None, None)) == None)
    assert(max(List(Some(5), None, Some(4), Some(1))) == Some(5))
  }
}
