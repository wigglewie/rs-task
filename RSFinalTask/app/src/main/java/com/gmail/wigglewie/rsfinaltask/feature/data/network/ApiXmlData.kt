package com.gmail.wigglewie.rsfinaltask.feature.data.network

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class ApiXmlData @JvmOverloads constructor(
    @field: Element(name = "channel")
    var channel: Channel? = null
)

@Root(name = "channel", strict = false)
class Channel @JvmOverloads constructor(
    @field: ElementList(inline = true)
    var itemList: List<ItemXml>? = null
)

@Root(name = "item", strict = false)
class ItemXml @JvmOverloads constructor(
    @field: Element(name = "title")
    var title: String? = null,
    @field: Element(name = "link", required = false)
    var link: String? = null,
    @field: Element(name = "description")
    var description: String? = null,
    @field: Element(name = "enclosure")
    var imageData: ImageData? = null,
    @field: Element(name = "pubDate")
    var pubDate: String? = null
)

@Root(name = "enclosure", strict = false)
class ImageData @JvmOverloads constructor(
    @field: Attribute(name = "url")
    var imageUrl: String? = null
)
