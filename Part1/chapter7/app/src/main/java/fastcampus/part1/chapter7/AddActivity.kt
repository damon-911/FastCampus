package fastcampus.part1.chapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import fastcampus.part1.chapter7.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private var originWord: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        binding.btnComplete.setOnClickListener {
            if (originWord == null)
                complete()
            else
                edit()
        }
    }

    private fun complete() {
        val text = binding.etWordInput.text.toString()
        val mean = binding.etWordMeanInput.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId)

        if (text == "" || mean == "" || type == null) {
            Toast.makeText(this, "입력하지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val word = Word(text, mean, type.text.toString())

        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread { Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show() }

            val intent = Intent().putExtra("isUpdated", true)
            setResult(RESULT_OK, intent)
            finish()
        }.start()
    }

    private fun edit() {
        val text = binding.etWordInput.text.toString()
        val mean = binding.etWordMeanInput.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId)

        if (text == "" || mean == "" || type == null) {
            Toast.makeText(this, "입력하지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val editWord = originWord?.copy(word = text, mean = mean, type = type.text.toString())

        Thread {
            editWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.update(word)
                runOnUiThread { Toast.makeText(this, "수정을 완료했습니다.", Toast.LENGTH_SHORT).show() }

                val intent = Intent().putExtra("editWord", editWord)
                setResult(RESULT_OK, intent)
                finish()
            }
        }.start()
    }

    private fun initViews() {
        val types = listOf("명사", "동사", "형용사", "대명사", "부사", "감탄사", "전치사", "접속사")

        binding.typeChipGroup.apply {
            types.forEach { text ->
                addView(createChip(text))
            }
        }

        binding.etWordInput.addTextChangedListener {
            it?.let { text ->
                binding.layoutWordInput.error = when (text.length) {
                    0 -> "값을 입력해주세요"
                    1 -> "2자 이상을 입력해주세요"
                    else -> null
                }
            }
        }

        originWord = intent.getParcelableExtra("originWord")
        originWord?.let { item ->
            binding.etWordInput.setText(item.word)
            binding.etWordMeanInput.setText(item.mean)
            val selectedChip = binding.typeChipGroup.children.firstOrNull { (it as Chip).text == item.type } as? Chip
            selectedChip?.isChecked = true
        }
    }

    private fun createChip(text: String) : Chip {
        return Chip(this).apply {
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }
}