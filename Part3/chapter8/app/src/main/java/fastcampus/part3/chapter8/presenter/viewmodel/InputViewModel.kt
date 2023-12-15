package fastcampus.part3.chapter8.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part3.chapter8.domain.model.Content
import fastcampus.part3.chapter8.domain.usecase.ContentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val contentUseCase: ContentUseCase
) : ViewModel() {

    private val _doneEvent = MutableLiveData<Pair<Boolean, String>>()
    val doneEvent: LiveData<Pair<Boolean, String>> = _doneEvent

    var category = MutableLiveData("")
    var title = MutableLiveData("")
    var content = MutableLiveData("")

    private var item: Content? = null

    fun initData(item: Content) {
        this.item = item
        category.value = item.category
        title.value = item.title
        content.value = item.content
    }

    fun insertData() {
        val categoryValue = category.value
        val titleValue = title.value
        val contentValue = content.value
        if (categoryValue.isNullOrBlank()
            || titleValue.isNullOrBlank()
            || contentValue.isNullOrBlank()
        ) {
            _doneEvent.value = Pair(false, "모든 항목을 입력하셔야 합니다.")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            contentUseCase.insert(
                item?.copy(
                    category = categoryValue,
                    title = titleValue,
                    content = contentValue,
                ) ?: Content(category = categoryValue, title = titleValue, content = contentValue)
            ).also {
                _doneEvent.postValue(Pair(true, if (it) "완료!" else "저장할 수 없습니다."))
            }
        }
    }
}