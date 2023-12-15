package fastcampus.part3.chapter8.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.Date

@BindingAdapter("date")
fun TextView.setDate(date: Date?) {
    text = DateUtil.convertPrintDateString(date)
}