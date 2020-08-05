package com.gmail.wigglewie.test1

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.ElementMap
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class ApiItems @JvmOverloads constructor(
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
    var title: String = "",
    @field: Element(name = "description", required = false)
    var description: String = "",
    @field: Element(name = "duration")
    var videoDuration: String = "",
    @field: Element(name = "enclosure")
    var videoInfo: VideoInfo? = null,
    @field: Element(name = "image")
    var imageInfo: ImageInfo? = null,
    @field: Element(name = "group")
    var creditList: Credit? = null
)

@Root(name = "group", strict = false)
class Credit @JvmOverloads constructor(
    @field: ElementMap(entry = "credit", key = "role", attribute = true, inline = true)
    var speaker: Map<String, String>? = null
)

@Root(name = "image")
class ImageInfo @JvmOverloads constructor(
    @field: Attribute(name = "url")
    var imageUrl: String = ""
)

@Root(name = "enclosure", strict = false)
class VideoInfo @JvmOverloads constructor(
    @field: Attribute(name = "url")
    var videoUrl: String = ""
)
