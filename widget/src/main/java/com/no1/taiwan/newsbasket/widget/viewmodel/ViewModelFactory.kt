package com.no1.taiwan.newsbasket.widget.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory

/**
 * A factory for creating a map to store a related [ViewModel]'s tag and the [ViewModel].
 */
class ViewModelFactory(
    application: Application,
    private val viewModels: LookUpViewModel
) : AndroidViewModelFactory(application) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = viewModels[modelClass] as T
}
