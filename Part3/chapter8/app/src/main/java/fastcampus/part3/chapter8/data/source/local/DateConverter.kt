package fastcampus.part3.chapter8.data.source.local

import androidx.room.TypeConverter
import fastcampus.part3.chapter8.util.DateUtil
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(timestamp: String?): Date? {
        return timestamp?.let { DateUtil.dbDateFormat.parse(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): String? {
        return DateUtil.dbDateFormat.format(date)
    }
}