package fastcampus.part3.chapter5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fastcampus.part3.chapter5.list.Common
import fastcampus.part3.chapter5.model.ImageItem
import fastcampus.part3.chapter5.model.ListItem
import fastcampus.part3.chapter5.model.VideoItem
import fastcampus.part3.chapter5.repository.SearchRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private var disposable: CompositeDisposable? = CompositeDisposable()

    private val _listLiveData = MutableLiveData<List<ListItem>>()
    val listLiveData: LiveData<List<ListItem>> get() = _listLiveData

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    fun search(query: String) {
        disposable?.add(searchRepository.search(query)
            .doOnSubscribe { _showLoading.value = true }
            .doOnTerminate { _showLoading.value = false }
            .subscribe({ list ->
                _listLiveData.value = list
            }, {
                _listLiveData.value = emptyList()
            })
        )
    }

    fun toggleFavorite(item: ListItem) {
        _listLiveData.value = _listLiveData.value?.map {
            if (it == item) {
                when (it) {
                    is ImageItem -> {
                        it.copy(isFavorite = !item.isFavorite)
                    }
                    is VideoItem -> {
                        it.copy(isFavorite = !item.isFavorite)
                    }
                    else -> {
                        it
                    }
                }.also { changeItem ->
                    if (Common.favoritesList.contains(item)) {
                        Common.favoritesList.remove(item)
                    } else {
                        Common.favoritesList.add(changeItem)
                    }
                }
            } else {
                it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        disposable = null
    }

    class SearchViewModelFactory(private val searchRepository: SearchRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(searchRepository) as T
        }
    }
}