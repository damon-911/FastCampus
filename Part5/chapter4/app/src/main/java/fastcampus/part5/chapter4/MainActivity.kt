package fastcampus.part5.chapter4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fastcampus.part5.chapter4.libraries.storage_contract.IStorage
import fastcampus.part5.chapter4.libraries.storage_contract.constants.StorageKey
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var storage: IStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        saveAppTheme()
    }

    private fun saveAppTheme() {
        storage.save(
            StorageKey.APP_THEME,
            BuildConfig.FLAVOR
        )
    }
}