package have.fun.scala3.basic.types.union

object ContentToText:

  def forUnion(content: ContentAsUnion): String =
    content match
      case TextContent(text) => text
      case ImageContent(url, alt) => alt.getOrElse(url)

  def forTrait(content: ContentAsTrait): String =
    content match
      case TextContentAsTrait(text) => text
      case ImageContentAsTrait(url, alt) => alt.getOrElse(url)
