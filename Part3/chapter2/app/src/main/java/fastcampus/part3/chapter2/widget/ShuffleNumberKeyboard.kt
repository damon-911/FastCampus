package fastcampus.part3.chapter2.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.children
import fastcampus.part3.chapter2.databinding.WidgetShuffleNumberKeyboardBinding
import kotlin.random.Random

class ShuffleNumberKeyboard @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    GridLayout(context, attrs, defStyleAttr), View.OnClickListener {

    interface KeyPadListener {
        fun onClickNum(num: String)
        fun onClickDelete()
        fun onClickDone()
    }

    private var _binding: WidgetShuffleNumberKeyboardBinding? = null
    private val binding get() = _binding!!

    private var keyPadListener: KeyPadListener? = null

    init {
        _binding = WidgetShuffleNumberKeyboardBinding.inflate(LayoutInflater.from(context), this, true)
        binding.view = this
        binding.clickListener = this
        shuffle()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    override fun onClick(v: View) {
        if (v is TextView && v.tag != null) {
            keyPadListener?.onClickNum(v.text.toString())
        }
    }

    private fun shuffle() {
        val keyNumberArr = ArrayList<String>()
        for (i in 0..9) {
            keyNumberArr.add(i.toString())
        }

        binding.gridLayout.children.forEach { view ->
            if (view is TextView && view.tag != null) {
                val randIndex = Random.nextInt(keyNumberArr.size)
                view.text = keyNumberArr[randIndex]
                keyNumberArr.removeAt(randIndex)
            }
        }
    }

    fun setKeyPadListener(keyPadListener: KeyPadListener) {
        this.keyPadListener = keyPadListener
    }

    fun onClickDelete() {
        keyPadListener?.onClickDelete()
    }

    fun onClickDone() {
        keyPadListener?.onClickDone()
    }
}