package fastcampus.part3.chapter2

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.gms.auth.api.phone.SmsRetriever
import fastcampus.part3.chapter2.databinding.ActivityVerifyOtpBinding
import fastcampus.part3.chapter2.util.AuthOtpReceiver
import fastcampus.part3.chapter2.util.ViewUtil.setOnEditorActionListener
import fastcampus.part3.chapter2.util.ViewUtil.showKeyboardDelay

class VerifyOtpActivity : AppCompatActivity(), AuthOtpReceiver.OtpReceiveListener {

    private lateinit var binding: ActivityVerifyOtpBinding

    private var smsReceiver: AuthOtpReceiver? = null

    private var timer: CountDownTimer? = object : CountDownTimer((3 * 60 * 1000).toLong(), 1000) {
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            val min = (millisUntilFinished / 1000) / 60
            val sec = (millisUntilFinished / 1000) % 60
            binding.timerTextView.text = "$min:${String.format("%02d", sec)}"
        }

        override fun onFinish() {
            binding.timerTextView.text = ""
            Toast.makeText(
                this@VerifyOtpActivity,
                "입력 제한시간을 초과하였습니다.\n다시 시도해주세요.",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun onResume() {
        super.onResume()
        binding.otpCodeEdit.showKeyboardDelay()
        startSmsRetriever()
    }

    override fun onDestroy() {
        clearTimer()
        stopSmsRetriever()
        super.onDestroy()
    }

    private fun initView() {
        startTimer()
        binding.view = this
        with(binding) {
            otpCodeEdit.doAfterTextChanged {
                if (otpCodeEdit.length() >= 6) {
                    stopTimer()
                    Toast.makeText(this@VerifyOtpActivity, "인증이 완료되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            otpCodeEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                // do not use
            }
        }
    }

    override fun onOtpReceived(otp: String) {
        binding.otpCodeEdit.setText(otp)
    }

    private fun startTimer() {
        timer?.start()
    }

    private fun stopTimer() {
        timer?.cancel()
    }

    private fun clearTimer() {
        stopTimer()
        timer = null
    }

    private fun startSmsRetriever() {
        SmsRetriever.getClient(this).startSmsRetriever().also { task ->
            task.addOnSuccessListener {
                if (smsReceiver == null) {
                    smsReceiver = AuthOtpReceiver().apply {
                        setOtpListener(this@VerifyOtpActivity)
                    }
                }
                registerReceiver(smsReceiver, smsReceiver!!.doFilter())
            }

            task.addOnFailureListener {
                stopSmsRetriever()
            }
        }
    }

    private fun stopSmsRetriever() {
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
            smsReceiver = null
        }
    }
}