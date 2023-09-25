package fastcampus.part3.chapter2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part3.chapter2.databinding.ActivityPinBinding
import fastcampus.part3.chapter2.widget.ShuffleNumberKeyboard

class PinActivity : AppCompatActivity(), ShuffleNumberKeyboard.KeyPadListener {

    private lateinit var binding: ActivityPinBinding
    private val viewModel: PinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.shuffleKeyboard.setKeyPadListener(this)

        viewModel.messageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickNum(num: String) {
        viewModel.input(num)
    }

    override fun onClickDelete() {
        viewModel.delete()
    }

    override fun onClickDone() {
        viewModel.done()
    }
}