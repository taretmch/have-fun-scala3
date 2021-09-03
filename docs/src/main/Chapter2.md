# 第二回: 文法 (2), インデントルール

## やること

- new キーワードについて
- Scala 3 で追加されたインデントルールについて
- ワイルドカード型について

## new キーワードについて

Scala 3 では、具象クラスには自動で `apply` メソッドが生えるようになった。

```scala mdoc
class ConstructorProxy(s: String):
  def this() = this("")
  def hello = println(s)

val c1 = ConstructorProxy("abc")
val c2 = ConstructorProxy()
c1.hello // => abc
c2.hello // =>
```

コンパイル時、コンパニオンオブジェクトに `apply` メソッドが追加される仕組みである。

```scala
// 生成されるコードのイメージ
object ConstructorProxy:
  inline def apply(s: String): ConstructorProxy = new ConstructorProxy(s)
  inline def apply(): ConstructorProxy = new ConstructorProxy()
```

この生成されるコンパニオンオブジェクトと `apply` メソッドは、**Constructor Proxy** と呼ばれる。Constructor Proxy は、Java のクラスや Scala 2 のクラスについても同様に生成される。

Constructor Proxy の生成規則は以下の通りである:

1. コンパニオンオブジェクトを持っておらず、かつそのクラスと同名の値やメソッドがスコープ内になければ、Constructor Proxy のコンパニオンオブジェクトが生成される。
2. Constructor Proxy の apply メソッドは、以下を満たす具象クラスに対して生成される:
  a. クラスがコンパニオンオブジェクト (1 で生成されたもの) を持っており、かつ
  b. コンパニオンオブジェクトが apply という名前のメンバーを定義していないこと。

なお、Constructor Proxy では case class によって生成される getter, equals, toString 等は使えない。

```scala
val cp = ConstructorProxy("abc")

cp.s
// 1 |cp.s
//   |^^^^
//   |value s cannot be accessed as a member of (cp : ConstructorProxy) from module class rs$line$4$.
 
cp == ConstructorProxy("abc")
// val res0: Boolean = false

cp.toString
// val res1: String = ConstructorProxy@34073423
```
