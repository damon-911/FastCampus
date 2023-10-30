package fastcampus.part3.chapter5.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class VideoListResponse(
    val documents: List<VideoItem>
)

data class VideoItem(
    @SerializedName("thumbnail") override val thumbnailUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("play_time") val playTime: Int,
    @SerializedName("author") val author: String,
    @SerializedName("datetime") override val dateTime: Date,
    @SerializedName("isFavorite") override var isFavorite: Boolean = false
) : ListItem

/**
 *
 * title	String	동영상 제목
 * url	String	동영상 링크
 * datetime	Datetime	동영상 등록일, ISO 8601
 * [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
 * play_time	Integer	동영상 재생시간, 초 단위
 * thumbnail	String	동영상 미리보기 URL
 * author	String	동영상 업로더
 **/