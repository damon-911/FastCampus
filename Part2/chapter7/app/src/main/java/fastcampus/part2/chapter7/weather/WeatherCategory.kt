package fastcampus.part2.chapter7.weather

import com.google.gson.annotations.SerializedName

enum class WeatherCategory {
    @SerializedName("POP")
    POP,    // 강수 확률

    @SerializedName("PTY")
    PTY,    // 강수 형태

    @SerializedName("SKY")
    SKY,    // 하늘 상태

    @SerializedName("TMP")
    TMP     // 1시간 기온
}