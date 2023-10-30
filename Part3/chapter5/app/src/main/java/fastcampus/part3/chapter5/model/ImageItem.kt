package fastcampus.part3.chapter5.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ImageListResponse(
    val documents: List<ImageItem>
)

data class ImageItem(
    @SerializedName("thumbnail_url") override val thumbnailUrl: String,
    @SerializedName("collection") val collection: String,
    @SerializedName("display_sitename") val siteName: String,
    @SerializedName("doc_url") val docUrl: String,
    @SerializedName("datetime") override val dateTime: Date,
    @SerializedName("isFavorite") override var isFavorite: Boolean = false
) : ListItem

/**
 *
 * collection	String	컬렉션
 * thumbnail_url	String	미리보기 이미지 URL
 * image_url	String	이미지 URL
 * width	Integer	이미지의 가로 길이
 * height	Integer	이미지의 세로 길이
 * display_sitename	String	출처
 * doc_url	String	문서 URL
 * datetime	Datetime	문서 작성시간, ISO 8601
 * [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
 **/