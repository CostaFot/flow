package com.feelsokman.androidtemplate.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderRepository
import com.feelsokman.androidtemplate.result.fold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoViewModel @Inject constructor(
    private val jsonPlaceHolderRepository: JsonPlaceHolderRepository
) : ViewModel() {

    val todoStateFlow = MutableStateFlow<String?>(value = null)
    val todoSharedFlow = MutableSharedFlow<String?>(replay = 0)

    fun updateTodoWithSharedFlow() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                jsonPlaceHolderRepository.getTodo().fold(
                    ifSuccess = {
                        todoSharedFlow.emit(it.title)
                    },
                    ifError = {
                        todoSharedFlow.emit(it.toString())
                    }
                )
            }
        }
    }

    fun updateTodoWithStateFlow() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                jsonPlaceHolderRepository.getTodo().fold(
                    ifSuccess = {
                        todoStateFlow.value = it.title
                    },
                    ifError = {
                        todoStateFlow.value = it.toString()
                    }
                )
            }
        }
    }
}
