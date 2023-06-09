package fastcampus.part1.chapter4

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layerBirthDate.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                var strMonth = month.inc().toString()
                if (month < 10)
                    strMonth = "0$strMonth"

                var strDay = dayOfMonth.toString()
                if (dayOfMonth < 10)
                    strDay = "0$strDay"

                binding.tvBirthValue.text = "$year-$strMonth-$strDay"
            }

            DatePickerDialog(
                this@InputActivity,
                listener,
                2000,
                1,
                1
            ).show()
        }

        binding.spBloodType.adapter = ArrayAdapter.createFromResource(
            this@InputActivity,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )
        
        binding.cbWarning.setOnCheckedChangeListener { _, isChecked ->
            binding.etWarningValue.isVisible = isChecked
        }

        binding.btnSave.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.etNameValue.text.toString())
            putString(BIRTH_DATE, binding.tvBirthValue.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(PHONE_NUM, binding.tvPhoneNumValue.text.toString())
            putString(WARNING, getWarning())
            apply()
        }

        Toast.makeText(this@InputActivity, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String {
        val bloodSign = if (binding.rgBloodTypeRhPlus.isChecked) "Rh+" else "Rh-"
        val bloodAlphabet = binding.spBloodType.selectedItem.toString()
        return "$bloodSign$bloodAlphabet"
    }

    private fun getWarning(): String {
        return if (binding.cbWarning.isChecked) binding.etWarningValue.text.toString() else ""
    }
}