package fastcampus.part2.chapter2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fastcampus.part2.chapter2.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity(), Timer.OnTimerTickListener {

    companion object {
        private const val REQUEST_RECORD_AUDIO_CODE = 200
    }

    // 릴리즈 -> 녹음중 -> 릴리즈
    // 릴리즈 -> 재생 -> 릴리즈
    private enum class State {
        RELEASE, RECORDING, PLAYING
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var timer: Timer

    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null
    private var fileName: String = ""
    private var state: State = State.RELEASE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
Log.d("qwer", "\"${externalCacheDir?.absolutePath}/audiorecordtest.3gp\"")
        fileName = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp"
        timer = Timer(this)

        binding.btnRecord.setOnClickListener {
            when (state) {
                State.RELEASE -> {
                    record()
                }
                State.RECORDING -> {
                    onRecord(false)
                }
                State.PLAYING -> {
                    // do nothing
                }
            }
        }

        binding.btnPlay.setOnClickListener {
            when (state) {
                State.RELEASE -> {
                    onPlay(true)
                }
                else -> {
                    // do nothing
                }

            }
        }

        binding.btnStop.setOnClickListener {
            when (state) {
                State.PLAYING -> {
                    onPlay(false)
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun record() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                // 녹음 시작
                onRecord(true)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.RECORD_AUDIO
            ) -> {
                showPermissionRationalDialog()
            }
            else -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE
                )
            }
        }
    }

    private fun onRecord(start: Boolean) = if (start) startRecording() else stopRecoding()

    private fun onPlay(start: Boolean) = if(start) startPlaying() else stopPlaying()

    private fun startRecording() {
        state = State.RECORDING

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e("APP", "prepare() failed $e")
            }

            start()
        }

        binding.waveFormView.clearData()
        timer.start()

        binding.btnRecord.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_pause_24
            )
        )

        binding.btnRecord.imageTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))

        binding.btnPlay.isEnabled = false
        binding.btnPlay.alpha = 0.3f
    }

    private fun stopRecoding() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null

        timer.stop()

        state = State.RELEASE

        binding.btnRecord.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_record_24
            )
        )

        binding.btnRecord.imageTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))

        binding.btnPlay.isEnabled = true
        binding.btnPlay.alpha = 1.0f
    }

    private fun startPlaying() {
        state = State.PLAYING

        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
            } catch (e: IOException) {
                Log.e("APP", "media player prepare fail $e")
            }

            start()
        }

        binding.waveFormView.clearWave()
        timer.start()

        player?.setOnCompletionListener {
            stopPlaying()
        }

        binding.btnRecord.isEnabled = false
        binding.btnRecord.alpha = 0.3f
    }

    private fun stopPlaying() {
        state = State.RELEASE

        player?.release()
        player = null

        timer.stop()

        binding.btnRecord.isEnabled = true
        binding.btnRecord.alpha = 1.0f
    }

    private fun showPermissionRationalDialog() {
        AlertDialog.Builder(this)
            .setTitle("녹음 권한 요청")
            .setMessage("녹음 권한을 허용해야 앱을 정상적으로 사용할 수 있습니다.")
            .setPositiveButton("권한 허용하기") { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE
                )
            }
            .setNegativeButton("취소") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }

    private fun showPermissionSettingDialog() {
        AlertDialog.Builder(this)
            .setMessage("녹음 권한을 허용해야 앱을 정상적으로 사용할 수 있습니다. 앱 설정 화면으로 진입하셔서 권한을 켜주세요.")
            .setPositiveButton("권한 변경하기") { _, _ ->
                navigateToAppSetting()
            }
            .setNegativeButton("취소") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()

    }

    private fun navigateToAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted = (requestCode == REQUEST_RECORD_AUDIO_CODE)
                && (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED)

        if (audioRecordPermissionGranted) {
            onRecord(true)
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.RECORD_AUDIO
                )
            ) {
                showPermissionRationalDialog()
            } else {
                showPermissionSettingDialog()
            }
        }
    }

    override fun onTick(duration: Long) {
        val millisecond = duration % 1000
        val second = (duration / 1000) % 60
        val minute = (duration / 1000 / 60)

        binding.tvTimer.text = String.format("%02d:%02d.%02d", minute, second, millisecond / 10)

        if (state == State.PLAYING) {
            binding.waveFormView.replayAmplitude()
        }
        else if (state == State.RECORDING) {
            binding.waveFormView.addAmplitude(recorder?.maxAmplitude?.toFloat() ?: 0f)
        }
    }
}