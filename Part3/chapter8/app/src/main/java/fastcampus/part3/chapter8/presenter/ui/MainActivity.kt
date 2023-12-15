package fastcampus.part3.chapter8.presenter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import fastcampus.part3.chapter8.databinding.ActivityMainBinding
import fastcampus.part3.chapter8.domain.model.Content
import fastcampus.part3.chapter8.presenter.ui.list.ListAdapter
import fastcampus.part3.chapter8.presenter.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter by lazy { ListAdapter(Handler()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayout.VERTICAL
                )
            )
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.contentList
                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .collect {
                    binding.apply {
                        progressBar.isVisible = false
                        emptyTextView.isVisible = it.isEmpty()
                        recyclerView.isVisible = it.isNotEmpty()
                    }
                    adapter.submitList(it)
                }
        }

        viewModel.doneEvent.observe(this) {
            if (it.first) {
                Toast.makeText(this, it.second, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onClickAdd() {
        InputActivity.start(this)
    }

    inner class Handler {
        fun onClickItem(item: Content) {
            InputActivity.start(this@MainActivity, item)
        }

        fun onLongClickItem(item: Content): Boolean {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("정말 삭제 하시겠습니까?")
                .setPositiveButton("네") { _, _ ->
                    viewModel.deleteItem(item)
                }.setNegativeButton("아니오") { _, _ ->

                }.show()
            return false
        }
    }
}