# 練習問題

## 問題1

以下のクラスに対して

```scala
class A:
  val value: Int = 5

class B(v: Int):
  val value: Int = v

class C(v: String):
  def this() = this("")
  def value: String = v
```

new キーワードを使わず、下記クラスのインスタンスを生成する以下のメソッドを実装してください。

```scala
object Question1:
  def getInstanceOfA(): A = ???
  def getInstanceOfB(v: Int): B = ???
  def getInstanceOfC(s: String): C = ???
  def getInstanceOfC(): C = ???
```

テスト

```scala
sbt:have-fun-scala3> exercise/testOnly exercise.chapter2.Question1Spec
```
