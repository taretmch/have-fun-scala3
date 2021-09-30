# 練習問題

## 問題1: Given インスタンス

順序に関する性質を定義した `Ord` という型クラスに対して

```scala
trait Ord[T]:
  def compare(x: T, y: T): Int
```

インスタンス `Ord[Int]` と `Ord[Option[T]]` を `Question1` オブジェクト内に定義してください。ただし、`compare` メソッドは以下の仕様とします。

- x が y より小さいなら -1 を返す
- x が y より大きいなら 1 を返す
- x と y が等しければ 0 を返す

### Ord[Option[T]]
- None は Some(_) よりも小さい
- Some(a) と Some(b) の大小は、a と b の大小に等しい。

### テスト

```scala
sbt:have-fun-scala3> exercise/testOnly exercise.chapter3.Question1Spec
```

## 問題2: Using パラメータ

`Ord` を型パラメータに持つリスト `List[T: Ord]` に対して、リスト内の最大の値を返すメソッド `max` を `Question2` オブジェクト内に定義してください。ただし、空リストに対しては例外を吐いていいものとします。

```scala
def max[T](list: List[T]): T = ...
```

### テスト

```scala
sbt:have-fun-scala3> exercise/testOnly exercise.chapter3.Question2Spec
```

## 問題3: 拡張メソッド

以下の拡張メソッドを `Question3` オブジェクト内に定義してください。

1. 型 `T: Ord` の値 `x` が `T` の値 `y` より小さいかどうかを返すメソッド: `x > y: Boolean`
2. 型 `T: Ord` の値 `x` が `T` の値 `y` より大きいかどうかを返すメソッド: `x < y: Boolean`
3. 型 `T: Ord` のリスト `list: List[T]` の最大値を返すメソッド: `list.max: T`

### テスト

```scala
sbt:have-fun-scala3> exercise/testOnly exercise.chapter3.Question3Spec
```
