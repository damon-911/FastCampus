package fastcampus.part2.chapter7

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication

        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }

    init {
        instance = this
    }
}