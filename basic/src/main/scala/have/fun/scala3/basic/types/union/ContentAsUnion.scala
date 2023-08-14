package have.fun.scala3.basic.types.union

type ContentAsUnion = TextContent | ImageContent

case class TextContent(text: String)

case class ImageContent(url: String, alt: Option[String])
