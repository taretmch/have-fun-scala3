# 第一回: TASTy, 文法 (1)

## 本日やること

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

以下の `Hello.scala` をコンパイルしてみよう。

```scala
@main def hello = println("Hello, world")
```

```sh
$ cs launch scala3-compiler -- Hello.scala
$ ls -l
Hello$package$.class
Hello$package.class
Hello$package.tasty
Hello.scala
hello.class
hello.tasty
```

## TASTy

TASTy (Typed Abstract Syntax Trees; 型付き抽象構文木) は、プログラムの文法構造、型情報等ソースコードの完全な情報を持つ。Scala 3 コンパイラは、`.scala` ファイルをコンパイルするとき、`.tasty` ファイルと `.class` ファイルを生成する。

### TASTy の中身を見てみよう

[scala3-tasty-inspector](https://github.com/lampepfl/dotty/tree/master/tasty-inspector/src/scala/tasty/inspector) は、`.tasty` ファイルをいい感じに整形してくれるライブラリである。[seed プロジェクト](https://github.com/scala/scala3-tasty-inspector.g8)を元に、先ほどの `Hello.tasty` ファイルの中身を出力してみよう。

```sh
$ pwd
/path/to/have-fun-scala3/lesson1/tasty-inspector

$ sbt inspector/run
```

すると、以下の出力が得られる。

```scala
@scala.annotation.internal.SourceFile("lib/src/main/scala/Hello.scala") final class hello() {
  def main(args: scala.Array[java.lang.String]): scala.Unit = try Hello$package.hello catch {
    case error: scala.util.CommandLineParser.ParseError =>
      scala.util.CommandLineParser.showError(error)
  }
}
```
