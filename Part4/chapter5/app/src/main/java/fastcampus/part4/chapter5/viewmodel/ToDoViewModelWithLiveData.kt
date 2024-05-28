package fastcampus.part4.chapter5.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fastcampus.part4.chapter5.data.ToDoData

class ToDoViewModelWithLiveData : ViewModel() {
    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    val setText: (String) -> Unit = {
        _text.value = it
    }

    // mutableStateListOf() : 추가, 삭제, 수정 -> 각 리스트 항목의 필드가 바뀌었을 경우에는 State가 갱신이 안됨
    // LiveData<List<...>>.observeAsState() : List가 통째로 다른 List로 바뀌었을 경우에만 State가 갱신됨
    private val _rawToDoList = mutableListOf<ToDoData>()
    private val _toDoList = MutableLiveData<List<ToDoData>>(_rawToDoList)
    val toDoList: LiveData<List<ToDoData>> = _toDoList

    val onSubmit: (String) -> Unit = {
        val key = (_rawToDoList.lastOrNull()?.key ?: 0) + 1
        _rawToDoList.add(ToDoData(key, it))
        _toDoList.value = mutableListOf<ToDoData>().also { list ->
            list.addAll(_rawToDoList)
        }
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(text = newText)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(done = checked)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList.removeAt(i)
        _toDoList.value = ArrayList(_rawToDoList)
    }
}