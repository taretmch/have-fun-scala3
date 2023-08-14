package have.fun.scala3.basic.types.union

type ContentAsUnion = TextContent | ImageContent

// To use this extension method, import this
extension (content: ContentAsUnion)
  def contentType: ContentType =
    content match
      case TextContent(_) => ContentType.Text
      case ImageContent(_, _) => ContentType.Image

case class TextContent(text: String):
  def contentType: ContentType = ContentType.Text

case class ImageContent(url: String, alt: Option[String]):
  def contentType: ContentType = ContentType.Image
