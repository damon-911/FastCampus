package fastcampus.part3.chapter5.bindingadapter

import android.icu.text.SimpleDateFormat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import fastcampus.part3.chapter5.R
import java.util.Date

@BindingAdapter("visible")
fun View.setVisible(isShow: Boolean) {
    isVisible = isShow
}

@BindingAdapter("image")
fun ImageView.setImage(imageUrl: String?) {
    load(imageUrl) {
        crossfade(300)
    }
}

@BindingAdapter("date")
fun TextView.setDateText(date: Date?) {
    if (date == null) {
        return
    }
    text = SimpleDateFormat("YYYY.MM.dd HH:mm:ss").format(date)
}

@BindingAdapter("favorite")
fun ImageView.setFavorite(isFavorite: Boolean) {
    setImageResource(if (isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
}