# 第二回: 文法 (2), インデントルール

## やること

New in Scala 3 [1] の続き。

- new キーワードについて
- Scala 3 で追加されたインデントルールについて
- ワイルドカード型について

## new キーワードについて

Scala 3 では、具象クラスには自動で `apply` メソッドが生えるようになった [2]。

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

## インデント規則

インデントの規則が追加されたことにより、 { } が必要でなくなった [3]。これにより、正しくインデントされていない場合、warning が出るようになった。正しくインデントされたプログラムは { ... } で囲まれたプログラムと全く同じである。

```scala
object Hoge:
  def hello(name: String): Unit =
    if (name.nonEmpty)
      println("Hello, %s".format(name))
      println("How are you today?")
    else
      println("Hello, world!")
      println("The blue sky is beautiful.")
    println("Good bye :)")
```

### 正しくインデントされたプログラム


1. 中括弧で区切られた範囲において、ブロックの最初の文のインデントよりも左にインデントしてはいけない
2. Scala 2 や -no-indent のオプションをつけており、インデントされた式のサブ部分が改行で終了しているとき、次の文はサブ部分より左にインデントがなければいけない

ダメな例:

```scala
def MaybeWarning(x: Int) =
  if (x < 0) {
    println(1)
    println(2)

  println(3)
  }

// [warn] 9 |    println(3)
// [warn]   |    ^
// [warn]   |    Line is indented too far to the left, or a `}` is missing

```

```scala
def MaybeError(x: Int) =
  if (x < 0)
    println(1)
    println(2)  // error: missing `{`
```

### インデント規則の仕組み

- インデントの開始行末には `<indent>` ( { と同じ意味) が入る
- インデントの終了行末には `<outdent>` ( } と同じ意味) が入る
- 現在のインデント位置は IW スタックで管理されている

```scala
object Hoge: // <indent>
  def hello(name: String): Unit = // <indent>
    if (name.nonEmpty) // <indent>
      println("Hello, %s".format(name))
      println("How are you today?") // <outdent>
    else // <indent>
      println("Hello, world!")
      println("The blue sky is beautiful.") // <outdent>
    println("Good bye :)") // <outdent>
    // `IW` stack has this indent width
```


以下が成り立つとき、`<indent>` が行末に入る。
1. インデント範囲が現在の位置から始まり、かつ
2. 次行の最初のトークンのインデント幅が、現在のインデント幅より大きいこと

なお、インデント範囲は、以下が成り立つときに始まる。
1. extension のパラメータの後であるか
2. given インスタンスにおける with の後であるか
3. 行末トークンの後であるか
4. 以下のトークンのいずれかのあとであるか
  `=  =>  ?=>  <-  catch  do  else  finally  for if  match  return  then  throw  try  while  yield`
5. if や while の条件式の閉じ括弧 ) の後であるか
6. for ループの enumerator の閉じ括弧 ) or } の後であること

```scala
// extension パラメータの後
extension (v: Seq[BigDecimal])  // <indent>
  def avg: BigDecimal = v.sum / v.length
```

```scala
// given インスタンスにおける with の後
given orderingLocalDateTime: Ordering[LocalDateTime] with  // <indent>
  def compare(x: LocalDateTime, y: LocalDateTime) =        // <indent>
    x.compareTo(y)
```

```scala
// 行末トークンの後
object endOfLine:  // <indent>
  val x = 1
```

```scala
// 特定のトークンの後
def hello: Int => Unit =
  x =>
    for
      y <-
        0 to 5
    do
      for
        z <-
            6 to 10
      yield
        if
          y % 2 == 0 then
            try
              throw
                new Exception("hoge error: " + y)
            catch
              case e =>
                println(e.getMessage)
            finally
              println("z is divided by y")
        else if
          y % 3 == 0 then
            println("multiple number of three")
        else
          x match
            case _ if x % 2 == 0 =>
              println("x is an even number")
            case _               =>
              println("x is an odd number")
```

```scala
// if や while の条件式の閉じ括弧 ) の後
if (x > 0)  // <indent>
  println("hello")

var y = 0
while (y < 5)  // <indent>
  println(y)
  y = y + 1
```

```scala
// for ループの enumerator の閉じ括弧 ) or } の後
for (x <- 1 to 5)  // <indent>
  yield println(x)

for {
  x <- 1 to 5
}  // <indent>
  yield println(x)
```

以下が成り立つとき、`<outdent>` が行末に入る。
1. 次の行の先頭トークンが現在のインデント幅より小さく
2. 前の行の末尾トークンが、以下のトークンではないこと
   `then  else  do  catch  finally  yield  match`
3. 次の行の先頭トークンが infix 演算子の場合で、そのインデント幅が現在のインデント幅より小さく、前のインデント幅と一致するか、終了インデント幅より小さいこと

あるいは、以下が成り立つときも `<outdent>` が入る。
1. 次のいずれかのトークンが `<indent>` で始まる文に続いており、そのインデント範囲を閉じること
   `then, else, do, catch, finally, yield, }, ), ], case`
2. インデント範囲が括弧によって閉じている場合、`<indent>` から始まる文に続くコンマの前に入る

```scala
// 次の行の先頭トークンが現在のインデント幅より小さく、前の行の末尾トークンが特定のトークンではないこと
if x < 0 then  // <indent>
  println("parameter is negative.")  // <outdent>
else  // <indent>
  println("parameter is positive.")  // <outdent>

extension (lhs: Option[Int])  // <indent>
  infix def + (rhs: Option[Int]): Option[Int] =  // <indent>
    for  // <indent>
      l <- lhs
      r <- rhs  // <outdent>
    yield  // <indent>
      l + r
```

```scala
// 次の行の先頭トークンが infix 演算子の場合、そのインデント幅が現在のインデント幅より小さく、前のインデント幅と一致するか、インデント範囲が終了するインデント幅より小さいこと
if x > 0 then  // <indent>
    Some(x)  // <outdent>
  + Some(2)
  + Some(3)  // <outdent>
else None
```

### Template Body

クラス、トレイト、オブジェクトを定義する template body に、括弧 { } が必要なくなった。代わりに、template body の前の行末に : を入れる必要がある。Enum の定義、package の定義にも同様の規則が導入された。

```scala
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
```

### スペースとタブの混在


インデント幅が「小さい」「大きい」という表現をしたが、スペースとタブが混在している場合、インデント幅を比較できるわけではない。

- 「2つのタブとそれに続く4つのスペース」は、「2つのタブとそれに続く5つのスペース」よりも厳密に小さくなる
- 「2つのタブとそれに続く4つのスペース」は、「6つのタブ」や「4つのスペース」と比較できない

混乱を防ぐために、なるべくどちらかに統一しよう。

### インデントと中括弧


インデントは、中括弧 {}、ブラケット []、括弧 () と自由に混在させることができる。そのような範囲でのインデントの解釈は、以下の規則に基づく。

1. 中括弧で囲まれた範囲のインデント幅は、中括弧の後に開始された行の最初のトークンのインデント幅になる
2. ブラケットまたは括弧で囲まれたインデント幅は以下のようになる
    a. 開き括弧 [ or ( が行末にある場合、それに続くトークンのインデント幅になる
    b. それ以外の場合は、囲んでいる括弧のインデント幅になる
3. 閉じ括弧 }, ] or ) が検出されると、必要な数の `<outdent>` が挿入される

### Case 句の特別な取り扱い

case に関するインデント規則

1. match や catch の後に、match と同じインデント幅を持つ case が続いた場合、インデント範囲が開始する
2. 上記の場合、case でない同じインデント幅のトークンにおいて、またはより小さいインデント幅のトークンにおいてインデント範囲が終了する

```scala
def f(x: Int) =
  // Indent region starts after this `match` because
  // the following `case` appears at the indentation width
  // that’s current for the match itself.
  x match
  case 1 => println("I")
  case 2 => println("II")
  case 3 => println("III")
  case 4 => println("IV")
  case 5 => println("V")
  case _ => println(x)
  println("indent region ended.")
```

### End マーカー

end マーカーは、インデント範囲の終了を表すトークンである。

```scala
def largeMethod(...) =
  ...
  if ... then ...
  else
    ... // a large block
  end if
  ... // more code
end largeMethod
```

end マーカーのあとに指定可能な指定子トークンは以下の通りである。

```scala
if   while    for    match    try    new    this    val   given
```

end マーカー指定子トークンはその前にある文に対応している必要がある。
1. 文がメンバー x を定義する場合、指定子トークンは x
2. 文がコンストラクターを定義する場合、指定子トークンは this
3. 文が匿名 given を定義する場合、指定子トークンは given
4. 文が匿名拡張メソッドを定義する場合、指定子トークンは extension
5. 文が匿名クラスを定義する場合、指定子トークンは new
6. 文が val 定義の場合、指定子トークンは val
7. 文が package 句の場合、指定子トークンは package の識別子
8. 文が if, while, for, try, あるいは match の場合、指定子トークンはそれと同じトークン

```scala
// package clause
package chapter2.end.marker:

  // member
  abstract class Member():
    // constructor
    def this(x: Int) =
      this()
      // if statement
      if x > 0 then
        // val definition
        val a :: b =
          x :: Nil
        end val
        // val definition
        var y =
          x
        end y
        // while statement
        while y > 0 do
          println(y)
          y -= 1
        end while
        // try statement
        try
          // match statement
          x match
            case 0 => println("0")
            case _ =>
          end match
        finally
          println("done")
        end try
      end if
    end this

    def f: String
  end Member

  // member
  object Member:
    // anonymous given
    given Member =
      // anonymous class
      new Member:
        // member
        def f = "!"
        end f
      end new
    end given
  end Member

  // anonymous extension
  extension (x: Member)
    def ff: String = x.f ++ x.f
  end extension

end marker
```

end マーカーは、インデント範囲が一目でわからない場合にオススメ。例えば
- インデント範囲が空白行を含む場合
- インデント範囲が15行〜20行以上の場合
- 4つ以上のインデントレベル等、大きくインデントされている場合

## ワイルドカード型について

型のワイルドカード引数の表記が `_` から `?` に変更された [4]。匿名の型パラメータの表記は `_` のまま。型コンストラクターの表記は `F[_]` のまま。

```scala
def f1: Int => Int = _ + 3
def f2(list: List[?]): Option[?] =
  list.headOption

trait Functor[F[_]] {

  def fmap[A, B](f: A => B): F[A] => F[B]
}
```

## 参考リンク

- [1] New in Scala 3, https://docs.scala-lang.org/scala3/new-in-scala3.html
- [2] Universal Apply Methods, https://docs.scala-lang.org/scala3/reference/other-new-features/creator-applications.html
- [3] Optional Braces, https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html
- [4] Wildcard Arguments in Types, https://docs.scala-lang.org/scala3/reference/changed-features/wildcards.html
