package fastcampus.part3.chapter4

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part3.chapter4.databinding.ActivityMainBinding
import fastcampus.part3.chapter4.mvc.MvcActivity
import fastcampus.part3.chapter4.mvi.MviActivity
import fastcampus.part3.chapter4.mvp.MvpActivity
import fastcampus.part3.chapter4.mvvm.MvvmActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.main = this
        }
    }

    fun openMvc() {
        startActivity(Intent(this, MvcActivity::class.java))
    }

    fun openMvp() {
        startActivity(Intent(this, MvpActivity::class.java))
    }

    fun openMvvm() {
        startActivity(Intent(this, MvvmActivity::class.java))
    }

    fun openMvi() {
        startActivity(Intent(this, MviActivity::class.java))
    }
}