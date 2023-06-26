package fastcampus.part2.chapter5.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class NewsRss(
    @Element(name = "channel")
    val channel: RssChannel
)

@Xml(name = "channel")
data class RssChannel(
    @PropertyElement(name = "title")
    val title: String,

    @Element(name = "item")
    val items: List<NewsItem>? = null
)

@Xml(name = "item")
data class NewsItem(
    @PropertyElement(name = "title")
    val title: String? = null,

    @PropertyElement(name = "link")
    val link: String? = null
)