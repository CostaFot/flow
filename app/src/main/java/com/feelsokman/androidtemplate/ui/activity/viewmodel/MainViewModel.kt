package com.feelsokman.androidtemplate.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderRepository
import com.feelsokman.androidtemplate.result.fold
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val jsonPlaceHolderRepository: JsonPlaceHolderRepository
) : ViewModel() {

    val textStateFlow = MutableStateFlow<String?>(null)

    fun getTodo() {
        viewModelScope.launch {
            jsonPlaceHolderRepository.getTodo().fold(
                ifSuccess = {
                    textStateFlow.value = it.title
                },
                ifError = {
                    textStateFlow.value = it.toString()
                }
            )
        }
    }
}
