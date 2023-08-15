package have.fun.scala3.basic.types.struct

/** Selectable トレイトの実装
  * このトレイトは、`selectDynamic` と `applyDynamic` という2つのメソッドを通じて構造的な型付けを実現する。
  *
  * `selectDynamic` はドットによるアクセスを提供する: `v.m`
  * `applyDynamic` は引数によるアクセスを提供する: `v.m(...)`
  */
class SelectableImpl(fields: (String, Any)*) extends Selectable:
  private val fieldMap = fields.toMap
  def selectDynamic(name: String): Any = fieldMap(name)

// SelectableImpl の部分型である Person 型を定義する。
type Person = SelectableImpl {
  val name: String
  val age: Int
}
