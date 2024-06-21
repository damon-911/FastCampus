package fastcampus.part5.chapter4.libraries.storage.sharedprefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefsStorageProvider @Inject constructor(@ApplicationContext context: Context) :
    StorageProvider {

    companion object {
        private const val PREF_KEY_NAME = "restaurant-app"
    }

    private val prefs = context.getSharedPreferences(
        PREF_KEY_NAME, Context.MODE_PRIVATE
    )

    override
    fun save(key: String, value: String): Boolean {
        return prefs.edit().putString(key, value).commit()
    }

    override
    fun get(key: String, default: String?): String? {
        return prefs.getString(key, default)
    }

    override
    fun remove(key: String): Boolean {
        prefs.edit().remove(key).apply()
        return true
    }

    override
    fun clear() {
        prefs.edit().clear().apply()
    }
}
