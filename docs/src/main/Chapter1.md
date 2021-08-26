# 第一回: TASTy, 文法 (1)

## やること

- Scala 3 コンパイラのインストール
- TASTy について学ぼう
- [New in Scala 3](https://docs.scala-lang.org/ja/scala3/new-in-scala3.html) を上から学んでいく

## Scala 3 インストール

[公式ガイド](https://docs.scala-lang.org/ja/scala3/getting-started.html)に従う。

```sh
$ brew update

# Coursier インストール
$ brew install coursier/formulas/coursier && cs setup

# コンパイラ、REPL インストール
$ cs install scala3-compiler
$ cs install scala3-repl

# REPL 起動してみよう
$ cs launch scala3-repl
scala> for i <- (1 to 10) do println(s"Hello $i")
Hello 1
Hello 2
Hello 3
Hello 4
Hello 5
Hello 6
Hello 7
Hello 8
Hello 9
Hello 10
```

### コンパイルしてみよう

以下の `Hello.scala` を作成し、コンパイルする。

```sh
$ pwd
/path/to/have-fun-scala3/src
$ vim Hello.scala
```

```scala
@main def hello = println("Hello, world")
```

```sh
$ cs launch scala3-compiler -- Hello.scala
```

生成されたファイルを見ると、`.tasty` ファイルというものが生成されているのがわかる。

```sh
$ ls -l
Hello$package$.class
Hello$package.class
Hello$package.tasty
Hello.scala
hello.class
hello.tasty
```

`.tasty` ファイルは Scala 3 コンパイラで生成するようになったファイルである。

## TASTy

TASTy (Typed Abstract Syntax Trees; 型付き抽象構文木) は、プログラムの文法構造、型情報等ソースコードの完全な情報を持つ。Scala 3 コンパイラは `.scala` ファイルをコンパイルするとき、TASTy の情報を持つ `.tasty` ファイルを生成するようになった。

### TASTy の中身を見てみよう

[scala3-tasty-inspector](https://github.com/lampepfl/dotty/tree/master/tasty-inspector/src/scala/tasty/inspector) は、`.tasty` ファイルをいい感じに整形してくれるライブラリである。[seed プロジェクト](https://github.com/scala/scala3-tasty-inspector.g8)を元に、先ほどの `Hello.tasty` ファイルの中身を出力してみる。

```sh
$ pwd
/path/to/have-fun-scala3

$ sbt "tastyInspector/runMain inspector.hello"
```

すると、以下の出力が得られる。

```scala
@scala.annotation.internal.SourceFile("chapters/src/main/scala/chapter1/Hello.scala") final class hello() {
  def main(args: scala.Array[java.lang.String]): scala.Unit = try Hello$package.hello catch {
    case error: scala.util.CommandLineParser.ParseError =>
      scala.util.CommandLineParser.showError(error)
  }
}
```

### TASTy がなぜ必要なのか

Scala 2 コンパイラは `.class` ファイルを生成するが、
[Type Erasure](https://www.scala-lang.org/files/archive/spec/2.13/03-types.html#type-erasure) のような問題で、`.class` ファイルは Scala ソースコードの完全な情報を持っていない。以下のコード `TypeErasure.scala` は、Type Erasure の例である。

```scala
val xs: List[Int] = List(1, 2, 3)
val x = xs(0)
```

これをコンパイルして生成された `.class` ファイルは、以下のような中身を持つ。

```sh
$ pwd
/path/to/have-fun-scala3/src
$ cs launch scala3-compiler -- TypeErasure.scala
$ javap TypeErasure\$package\$
Compiled from "TypeErasure.scala"
public final class TypeErasure$package$ implements java.io.Serializable {
  public static final TypeErasure$package$ MODULE$;
  public static {};
  public scala.collection.immutable.List<java.lang.Object> xs();
  public int x();
}
```

`List[Int]` が `List<java.lang.Object>` に変わっており、リストの型パラメータ `Int` の情報がなくなっている。一方で、その要素である `x` は `Int` にキャストされて取得されている。

このコードの TASTy を出力すると、以下のようになる。`xs` の型は `List[Int]` のままである。

```sh
$ pwd
/path/to/have-fun-scala3
$ sbt "tastyInspector/runMain inspector.typeErasure"
...
@scala.annotation.internal.SourceFile("chapters/src/main/scala/chapter1/TypeErasure.scala") object TypeErasure$package {
  val xs: scala.List[scala.Int] = scala.List.apply[scala.Int](1, 2, 3)
  val x: scala.Int = TypeErasure$package.xs.apply(0)
}
```

### TASTy のメリット

- .tasty ファイルを通して、Scala 2.13.6 から Scala 3 のコードを利用できる (TASTy Reader)
- 分割コンパイルをサポートできるようになるらしい
- LSP (Language Server Protocol) で利用できるらしい
- マクロの生成方法に関係しているらしい
- コード最適化ツール、解析ツールに利用できるらしい

## 文法

### 制御構文の新文法

[New Control Syntax](https://docs.scala-lang.org/scala3/reference/other-new-features/control-syntax.html) より。Scala 3 では、制御構文をよりシンプルに書けるように文法が改善された。

### If

then があれば、条件式にカッコが必要なくなった。

```scala mdoc
def ifFunc1(x: Int) =
  if x < 0 then
    "negative"
  else if x == 0 then
    "zero"
  else
    "positive"

def ifFunc2(x: Int) =
  if x < 0 then -x else x

ifFunc1(3)
ifFunc1(0)
ifFunc1(-4)
ifFunc2(-5)
```

### for

yield か do をつければ、enumerator にカッコが必要なくなった。また、for ループを表す、do が追加された。

```scala mdoc
def forFunc1(xs: Seq[Int]) =
  for x <- xs if x > 0 yield x * x

def forFunc2(xs: Seq[Int], ys: Seq[Int]) =
  for
    x <- xs
    y <- ys
  do
    println(x + y)

forFunc1(List(1, 2, 3, 4, 5))
forFunc1(List(1, 2, 3, 4, 5), List(6, 7, 8, 9, 10))
```

### while

do があれば、条件式にカッコが必要なくなった。

```scala mdoc
def whileFunc1(x: Int, f: Int => Int) =
  var tmp = x
  while
    tmp >= 0
  do
    println(tmp)
    tmp = f(tmp)

whileFunc1(5, _ - 1)
```

### try

1つの case であれば、1行で書けるようになった。

```scala mdoc
try throw new IOException
catch case ex: IOException => println("error")
```
