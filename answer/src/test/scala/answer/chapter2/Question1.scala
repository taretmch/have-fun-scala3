package answer.chapter2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import Question1._

class Question1Spec extends AnyFlatSpec with Matchers {

  "getInstanceOfA 関数" should "クラス A のインスタンスを返す" in {
    assert(getInstanceOfA().value == 5)
  }

  "getInstanceOfB 関数" should "クラス B のインスタンスを返す" in {
    assert(getInstanceOfB(1).value == 1)
  }

  "getInstanceOfC 関数" should "クラス C のインスタンスを返す" in {
    assert(getInstanceOfC("hello").value == "hello")
    assert(getInstanceOfC().value == "")
  }
}
