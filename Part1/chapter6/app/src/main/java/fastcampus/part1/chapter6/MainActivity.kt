package fastcampus.part1.chapter6

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import fastcampus.part1.chapter6.databinding.ActivityMainBinding
import fastcampus.part1.chapter6.databinding.DialogCountdownBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var timer: Timer? = null
    private var countDownSecond = 10
    private var currentCountDownDeciSecond = countDownSecond * 10
    private var currentDeciSecond = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCountDown()

        binding.tvCountDown.setOnClickListener {
            showCountDownDialog()
        }

        binding.btnStart.setOnClickListener {
            start()
            binding.btnStart.isVisible = false
            binding.btnStop.isVisible = false
            binding.btnPause.isVisible = true
            binding.btnCheck.isVisible = true
        }

        binding.btnStop.setOnClickListener {
            showAlertDialog()
        }

        binding.btnPause.setOnClickListener {
            pause()
            binding.btnStart.isVisible = true
            binding.btnStop.isVisible = true
            binding.btnPause.isVisible = false
            binding.btnCheck.isVisible = false
        }

        binding.btnCheck.setOnClickListener {
            check()
        }
    }

    private fun initCountDown() {
        binding.tvCountDown.text = String.format("%02d", countDownSecond)
        binding.progressBar.progress = 100
    }

    private fun showCountDownDialog() {
        AlertDialog.Builder(this@MainActivity).apply {
            val dialogBinding = DialogCountdownBinding.inflate(layoutInflater)
            with(dialogBinding.countDownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countDownSecond
            }
            setView(dialogBinding.root)

            setTitle("카운트다운 설정")
            setPositiveButton("확인") { _, _ ->
                countDownSecond = dialogBinding.countDownSecondPicker.value
                currentCountDownDeciSecond = countDownSecond * 10
                binding.tvCountDown.text = String.format("%02d", countDownSecond)
            }
            setNegativeButton("취소", null)
        }.show()
    }

    private fun start() {
        timer = timer(initialDelay = 0, period = 100) {
            if (currentDeciSecond == 0 && currentCountDownDeciSecond < 31 && currentCountDownDeciSecond % 10 == 0) {
                val toneType = if (currentCountDownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
                ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                    .startTone(toneType, 100)
            }

            if (currentCountDownDeciSecond == 0) {
                currentDeciSecond += 1

                val minutes = currentDeciSecond.div(10) / 60
                val seconds = currentDeciSecond.div(10) % 60
                val deciSeconds = currentDeciSecond % 10

                runOnUiThread {
                    binding.tvTime.text = String.format("%02d:%02d", minutes, seconds)
                    binding.tvTick.text = deciSeconds.toString()
                    binding.countDownGroup.isVisible = false
                }
            } else {
                currentCountDownDeciSecond -= 1
                val currentSeconds = currentCountDownDeciSecond / 10
                val progress = (currentCountDownDeciSecond / (countDownSecond * 10f)) * 100

                binding.root.post {
                    binding.tvCountDown.text = String.format("%02d", currentSeconds)
                    binding.progressBar.progress = progress.toInt()
                }
            }
        }
    }

    private fun stop() {
        binding.btnStart.isVisible = true
        binding.btnStop.isVisible = true
        binding.btnPause.isVisible = false
        binding.btnCheck.isVisible = false

        currentDeciSecond = 0
        binding.tvTime.text = "00:00"
        binding.tvTick.text = "0"

        countDownSecond = 10
        currentCountDownDeciSecond = 100
        binding.countDownGroup.isVisible = true

        binding.lapLinearLayout.removeAllViews()

        initCountDown()
    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    private fun check() {
        if (currentDeciSecond == 0)
            return

        val container = binding.lapLinearLayout
        TextView(this@MainActivity).apply {
            textSize = 20f
            gravity = Gravity.CENTER
            val minutes = currentDeciSecond.div(10) / 60
            val seconds = currentDeciSecond.div(10) % 60
            val deciSeconds = currentDeciSecond % 10
            text = container.childCount.inc().toString() + String.format(". %02d:%02d %01d", minutes, seconds, deciSeconds)
            setPadding(30)
        }.let {
            container.addView(it, 0)
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this@MainActivity).apply {
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, _ ->
                stop()
            }
            setNegativeButton("아니오", null)
        }.show()
    }
}