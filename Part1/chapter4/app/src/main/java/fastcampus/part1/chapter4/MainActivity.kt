package fastcampus.part1.chapter4

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInput.setOnClickListener {
            // 명시적 인텐트
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            deleteData()
        }

        binding.layerPhoneNum.setOnClickListener {
            // 암시적 인텐트
            with(Intent(Intent.ACTION_VIEW)) {
                val phoneNumber = binding.tvPhoneNumValue.text.toString().replace("-", "")
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getDataAndUpdate()
    }

    private fun getDataAndUpdate() {
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)) {
            binding.tvNameValue.text = getString(NAME, "-")
            binding.tvBirthValue.text = getString(BIRTH_DATE, "-")
            binding.tvBloodTypeValue.text = getString(BLOOD_TYPE, "-")
            binding.tvPhoneNumValue.text = getString(PHONE_NUM, "-")

            val warningInfo = getString(WARNING, "")
            binding.tvWarning.isVisible = warningInfo.isNullOrEmpty().not()
            binding.tvWarningValue.isVisible = warningInfo.isNullOrEmpty().not()
            if (!warningInfo.isNullOrEmpty()) {
                binding.tvWarningValue.text = warningInfo
            }
        }
    }

    private fun deleteData() {
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit()) {
            clear()
            apply()
            getDataAndUpdate()
        }

        Toast.makeText(this@MainActivity, "초기화가 완료되었습니다.", Toast.LENGTH_SHORT).show()
    }
}