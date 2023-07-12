package fastcampus.part2.chapter11.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import fastcampus.part2.chapter11.R
import fastcampus.part2.chapter11.databinding.ItemMenuBinding

class MenuView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    private lateinit var binding: ItemMenuBinding

    private var title: String? = null
    private var imageUrl: String? = null

    init {
        attributeSet?.let {
            initAttr(it)
        }
        initView()
    }

    private fun initAttr(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MenuView,
            0, 0
        ).apply {
            title = getString(R.styleable.MenuView_title)
            imageUrl = getString(R.styleable.MenuView_imageUrl)
        }
    }

    private fun initView() {
        val view = inflate(context, R.layout.item_menu, this)
        binding = ItemMenuBinding.bind(view)

        title?.let {
            setTitle(it)
        }

        imageUrl?.let {
            setImageUrl(it)
        }
    }

    fun setTitle(title: String) {
        this.title = title
        binding.nameTextView.text = title
    }

    fun setImageUrl(imageUrl: String) {
        this.imageUrl = imageUrl
        Glide.with(binding.imageView)
            .load(imageUrl)
            .circleCrop()
            .into(binding.imageView)
    }
}