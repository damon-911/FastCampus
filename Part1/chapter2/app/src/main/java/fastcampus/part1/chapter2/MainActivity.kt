package fastcampus.part1.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvNum = findViewById<TextView>(R.id.tv_num)
        val btnReset = findViewById<Button>(R.id.btn_reset)
        val btnPlus = findViewById<Button>(R.id.btn_plus)

        var number = 0

        btnReset.setOnClickListener {
            Log.d("MainActivity", "리셋이 완료되었습니다.")
            Toast.makeText(this@MainActivity, "리셋이 완료되었습니다.", Toast.LENGTH_SHORT).show()

            number = 0
            tvNum.text = number.toString()
        }

        btnPlus.setOnClickListener {
            Log.i("MainActivity", "숫자 플러스!")
            Toast.makeText(this@MainActivity, "숫자 플러스!", Toast.LENGTH_SHORT).show()

            number += 1
            tvNum.text = number.toString()
        }
    }
}