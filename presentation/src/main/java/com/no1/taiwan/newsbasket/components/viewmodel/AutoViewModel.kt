package com.no1.taiwan.newsbasket.components.viewmodel

import androidx.lifecycle.ViewModel
import com.devrapid.kotlinshaver.accessible
import com.no1.taiwan.newsbasket.domain.DeferredUsecase

open class AutoViewModel : ViewModel() {
    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        // Search all variables including private.
        this::class.java.declaredFields.forEach {
            val usecaseSuperclass = it.type.superclass

            // Get variables are the [DeferredUsecase] class for aborting the action.
            if (DeferredUsecase::class.java == usecaseSuperclass) {
                val method = usecaseSuperclass.getMethod("abort")
                it.accessible()
//                method.invoke(it.get(this))
            }
        }
    }
}
