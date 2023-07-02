package fastcampus.part2.chapter8

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        MyApplication.applicationContext = applicationContext
    }
}