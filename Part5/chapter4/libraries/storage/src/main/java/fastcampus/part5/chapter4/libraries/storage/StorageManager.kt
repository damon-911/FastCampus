package fastcampus.part5.chapter4.libraries.storage

import android.text.TextUtils
import fastcampus.part5.chapter4.libraries.storage.helper.DataConverter
import fastcampus.part5.chapter4.libraries.storage.helper.DataEncoding
import fastcampus.part5.chapter4.libraries.storage.sharedprefs.StorageProvider
import fastcampus.part5.chapter4.libraries.storage_contract.IStorage

class StorageManager(
    private val storage: StorageProvider,
    private val converter: DataConverter,
    private val encoding: DataEncoding,
) : IStorage {

    override fun <T> save(key: String, value: T): Boolean {
        var saved = false
        val serializedData = converter.serialize(value)
        if (serializedData != null) {
            encoding.encodeToString(serializedData)?.also { data ->
                saved = storage.save(key, data)
            }
        }
        return saved
    }

    override fun <T> save(key: String, value: T, callback: (Boolean) -> Unit) {
        callback(save(key, value))
    }

    override fun exists(key: String): Boolean {
        return storage.get(key) != null
    }

    override fun <T> get(key: String): T? {
        val savedData = storage.get(key)
        var data: T? = null
        if (!TextUtils.isEmpty(savedData)) {
            data = converter.deserialize<T>(encoding.decode(savedData))
        }
        return data
    }

    override fun <T> get(key: String, callback: (data: T?) -> Unit) {
        callback(get(key))
    }

    override fun remove(key: String): Boolean {
        return storage.remove(key)
    }

    override fun remove(key: String, callback: (Boolean) -> Unit) {
        callback(remove(key))
    }

    override fun clear(): Boolean {
        storage.clear()
        return true
    }
}