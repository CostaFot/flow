package com.feelsokman.androidtemplate.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderRepository

class DoSomethingWorkerFactory(
    private val jsonPlaceHolderRepository: JsonPlaceHolderRepository
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            DoSomethingWorker::class.java.name ->
                DoSomethingWorker(appContext, workerParameters, jsonPlaceHolderRepository)
            else ->
                // Return null, so that the base class can delegate to the default WorkerFactory.
                null
        }
    }
}
