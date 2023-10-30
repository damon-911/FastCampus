package fastcampus.part3.chapter5.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fastcampus.part3.chapter5.databinding.FragmentSearchBinding
import fastcampus.part3.chapter5.list.ItemHandler
import fastcampus.part3.chapter5.list.ListAdapter
import fastcampus.part3.chapter5.model.ListItem
import fastcampus.part3.chapter5.repository.SearchRepositoryImpl
import fastcampus.part3.chapter5.service.RetrofitManager
import fastcampus.part3.chapter5.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModel.SearchViewModelFactory(SearchRepositoryImpl(RetrofitManager.searchService))
    }

    private var binding: FragmentSearchBinding? = null

    private val adapter by lazy { ListAdapter(Handler(viewModel)) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            recyclerView.adapter = adapter
        }
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun searchKeyword(text: String) {
        viewModel.search(text)
    }

    private fun observeViewModel() {
        viewModel.listLiveData.observe(viewLifecycleOwner) {
            binding?.apply {
                if (it.isEmpty()) {
                    emptyTextView.isVisible = true
                    recyclerView.isVisible = false
                } else {
                    emptyTextView.isVisible = false
                    recyclerView.isVisible = true
                }
            }
            adapter.submitList(it)
        }
    }

    class Handler(private val viewModel: SearchViewModel) : ItemHandler {
        override fun onClickFavorite(item: ListItem) {
            viewModel.toggleFavorite(item)
        }
    }
}