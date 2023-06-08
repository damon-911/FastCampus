package fastcampus.part1.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import fastcampus.part1.chapter3.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var inputNum: Int = 0
    private var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        val etInput = binding.etInput
        val tvOutput = binding.tvOutput
        val tvInputUnit = binding.tvInputUnit
        val tvOutputUnit = binding.tvOutputUnit
        val ibSwap = binding.ibSwap

        etInput.addTextChangedListener { text ->
            inputNum = if (text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            if (cmToM) {
                tvOutput.text = df.format(inputNum.times(0.01)).toString()
            } else {
                tvOutput.text = inputNum.times(100).toString()
            }
        }

        ibSwap.setOnClickListener {
            cmToM = !cmToM

            if (cmToM) {
                tvInputUnit.text = "cm"
                tvOutputUnit.text = "m"
                tvOutput.text = df.format(inputNum.times(0.01)).toString()
            } else {
                tvInputUnit.text = "m"
                tvOutputUnit.text = "cm"
                tvOutput.text = inputNum.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")

        binding.tvInputUnit.text = if (cmToM) "cm" else "m"
        binding.tvOutputUnit.text = if (cmToM) "m" else "cm"

        super.onRestoreInstanceState(savedInstanceState)
    }
}