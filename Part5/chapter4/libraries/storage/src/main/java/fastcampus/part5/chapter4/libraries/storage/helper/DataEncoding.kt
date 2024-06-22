package fastcampus.part5.chapter4.libraries.storage.helper

import java.util.Base64
import javax.inject.Inject

class DataEncoding @Inject constructor() {

    fun encodeToString(data: ByteArray?): String? {
        return Base64.getEncoder().encodeToString(data)
    }

    fun decode(data: String?): ByteArray? {
        return Base64.getDecoder().decode(data)
    }
}
