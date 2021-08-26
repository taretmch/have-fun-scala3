# 第一回: TASTy, 文法 (1)

## やること

- Scala 3 コンパイラのインストール
- TASTy について学ぼう
- [New in Scala 3](https://docs.scala-lang.org/ja/scala3/new-in-scala3.html) の「新機能 & 特長: 文法」

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

- 
