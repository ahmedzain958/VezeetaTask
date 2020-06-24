package com.vezeeta.vezeetatask.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    private val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    // Cancel the job when the view model is cleared
    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}