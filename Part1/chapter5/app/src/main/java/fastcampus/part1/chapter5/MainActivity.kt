package fastcampus.part1.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import fastcampus.part1.chapter5.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val firstNumberText = StringBuilder("")
    private val secondNumberText = StringBuilder("")
    private val operatorText = StringBuilder("")
    private val decimalFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun numberClicked(view: View) {
        val numberString = (view as? Button)?.text?.toString() ?: ""
        val numberText = if (operatorText.isEmpty()) firstNumberText else secondNumberText

        numberText.append(numberString)
        updateEquation()
    }

    fun operatorClicked(view: View) {
        val operatorString = (view as? Button)?.text?.toString() ?: ""

        if (firstNumberText.isEmpty()) {
            Toast.makeText(this@MainActivity, "숫자를 먼저 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (secondNumberText.isNotEmpty()) {
            if (binding.tvResult.text.isNotEmpty()) {
                clearEquation()
                firstNumberText.append(binding.tvResult.text.toString().replace(",", ""))
                binding.tvResult.text = ""
            }
            else {
                Toast.makeText(this@MainActivity, "1개의 연산자에 대해서만 연산이 가능합니다.", Toast.LENGTH_SHORT).show()
                return
            }
        }

        operatorText.append(operatorString)
        updateEquation()
    }

    fun equalClicked(view: View) {
        if (firstNumberText.isEmpty() || secondNumberText.isEmpty() || operatorText.isEmpty()) {
            Toast.makeText(this@MainActivity, "올바르지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val firstNumber = firstNumberText.toString().toBigDecimal()
        val secondNumber = secondNumberText.toString().toBigDecimal()

        val result = when (operatorText.toString()) {
            "+" -> decimalFormat.format(firstNumber + secondNumber)
            "-" -> decimalFormat.format(firstNumber - secondNumber)
            else -> "잘못된 수식"
        }

        binding.tvResult.text = result
    }

    fun clearClicked(view: View) {
        binding.tvResult.text = ""
        clearEquation()
        updateEquation()
    }

    private fun updateEquation() {
        val firstNumberFormatted = if (firstNumberText.isNotEmpty()) decimalFormat.format(firstNumberText.toString().toBigDecimal()) else ""
        val secondNumberFormatted = if (secondNumberText.isNotEmpty()) decimalFormat.format(secondNumberText.toString().toBigDecimal()) else ""

        binding.tvEquation.text = "$firstNumberFormatted $operatorText $secondNumberFormatted"
    }

    private fun clearEquation() {
        firstNumberText.clear()
        secondNumberText.clear()
        operatorText.clear()
    }
}