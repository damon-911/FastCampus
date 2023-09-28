package fastcampus.part3.chapter3

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import fastcampus.part3.chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.thirdCard.setOnClickListener(getCardClickListener(R.id.thirdCardOnTop))
        binding.secondCard.setOnClickListener(getCardClickListener(R.id.secondCardOnTop))
        binding.firstCard.setOnClickListener(getCardClickListener(R.id.firstCardOnTop))
    }

    private fun getCardClickListener(@IdRes endStateId: Int) = OnClickListener {
        with(binding) {
            when (root.currentState) {
                R.id.fanOut -> {
                    root.setTransition(R.id.fanOut, R.id.firstCardOnTop)
                    root.transitionToEnd()
                    collapsedCardCompletedListener(endStateId)
                }
                R.id.thirdCardOnTop -> {
                    if (R.id.thirdCardOnTop == endStateId) {
                        openDetail(thirdCard, thirdCardTitleTextView.text)
                    } else {
                        root.setTransition(R.id.thirdCardOnTop, endStateId)
                        root.transitionToEnd()
                    }
                }
                R.id.secondCardOnTop -> {
                    if (R.id.secondCardOnTop == endStateId) {
                        openDetail(secondCard, secondCardTitleTextView.text)
                    } else {
                        root.setTransition(R.id.secondCardOnTop, endStateId)
                        root.transitionToEnd()
                    }
                }
                R.id.firstCardOnTop -> {
                    if (R.id.firstCardOnTop == endStateId) {
                        openDetail(firstCard, firstCardTitleTextView.text)
                    } else {
                        root.setTransition(R.id.firstCardOnTop, endStateId)
                        root.transitionToEnd()
                    }
                }
            }
        }
    }

    private fun openDetail(view: View, cardName: CharSequence) {
        view.transitionName = "card"
        val optionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@MainActivity,
                Pair(view, view.transitionName)
            )
        DetailActivity.start(this, cardName.toString(), view.backgroundTintList, optionsCompat)
    }

    private fun collapsedCardCompletedListener(@IdRes endStateId: Int) {
        with(binding) {
            root.setTransitionListener(object : TransitionAdapter() {

                override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                    if (currentId == R.id.firstCardOnTop) {
                        root.setTransition(R.id.firstCardOnTop, endStateId)
                        root.transitionToEnd()
                    }
                    root.setTransitionListener(null)
                }
            })
        }
    }
}