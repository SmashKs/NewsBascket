package com.no1.taiwan.newsbasket.features.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devrapid.kotlinshaver.gLaunch
import com.no1.taiwan.newsbasket.domain.usecases.FetchAdBlockListCase
import com.no1.taiwan.newsbasket.ext.exec

class NewsDetailViewModel(
    private val adBlockListCase: FetchAdBlockListCase
) : ViewModel() {
    private val _blockListLiveData by lazy { MutableLiveData<List<String>>() }
    val blockListLiveData = _blockListLiveData

    fun fetchBlockList() = gLaunch {
        _blockListLiveData.postValue(adBlockListCase.exec())
    }
}
