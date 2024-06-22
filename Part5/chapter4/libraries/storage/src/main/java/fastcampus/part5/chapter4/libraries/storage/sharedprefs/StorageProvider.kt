package fastcampus.part5.chapter4.libraries.storage.sharedprefs

interface StorageProvider {
    fun save(key: String, value: String): Boolean
    fun get(key: String, default: String? = null): String?
    fun remove(key: String): Boolean
    fun clear()
}
