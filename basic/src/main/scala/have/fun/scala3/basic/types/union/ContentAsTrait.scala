package have.fun.scala3.basic.types.union

sealed trait ContentAsTrait:
  val contentType: ContentType

case class TextContentAsTrait(text: String) extends ContentAsTrait:
  val contentType = ContentType.Text

case class ImageContentAsTrait(url: String, alt: Option[String]) extends ContentAsTrait:
  val contentType = ContentType.Image
