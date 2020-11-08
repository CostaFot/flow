package com.feelsokman.androidtemplate.work

import androidx.work.DelegatingWorkerFactory
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateWorkerFactory @Inject constructor(
    jsonPlaceHolderRepository: JsonPlaceHolderRepository
) : DelegatingWorkerFactory() {
    init {
        addFactory(DoSomethingWorkerFactory(jsonPlaceHolderRepository))
    }
}
