package have.fun.scala3.basic.types.union

val text = TextContent("Hello, world!")
val image = ImageContent("https://example.com/image.png", Some("Example"))
val textAsTrait = TextContentAsTrait("Hello, world!")
val imageAsTrait = ImageContentAsTrait("https://example.com/image.png", Some("Example"))

val content = if true then text else image
//=> TextContent | ImageContent

val contentAsTrait = if true then textAsTrait else imageAsTrait
//=> ContentAsTrait

val contentAsUnion: TextContentAsTrait | ImageContentAsTrait = if true then textAsTrait else imageAsTrait
//=> TextContentAsTrait | ImageContentAsTrait
