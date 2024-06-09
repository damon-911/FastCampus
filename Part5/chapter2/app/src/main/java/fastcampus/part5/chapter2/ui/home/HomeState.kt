package fastcampus.part5.chapter2.ui.home

import android.content.Intent
import fastcampus.part5.chapter2.ui.content.ContentActivity

class HomeState(private val activity: HomeActivity) {
    fun showContent(index: Int) {
        activity.startActivity(Intent(activity, ContentActivity::class.java).apply {
            putExtra("id", index)
        })
    }
}