package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderRepository
import com.feelsokman.androidtemplate.result.fold
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HostViewModel @Inject constructor(
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
