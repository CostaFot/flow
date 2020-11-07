package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.result.fold
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HostViewModel @Inject constructor(
    private val jsonPlaceHolderClient: JsonPlaceHolderClient
) : ViewModel() {

    val textData = MutableStateFlow<String?>(null)

    fun getTodo() {
        viewModelScope.launch {
            jsonPlaceHolderClient.getTodo().fold(
                ifSuccess = {
                    textData.value = it.title
                },
                ifError = {
                    textData.value = it.toString()
                }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
