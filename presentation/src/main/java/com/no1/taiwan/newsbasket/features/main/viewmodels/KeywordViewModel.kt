package com.no1.taiwan.newsbasket.features.main.viewmodels

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchKeywordsUsecase
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun

class KeywordViewModel(
    private val fetchUsecase: FetchKeywordsUsecase,
    private val addUsecase: AddKeywordUsecase,
    private val deleteUsecase: DeleteKeywordUsecase
) : AutoViewModel() {
    private val _keywordsLiveData by lazy { ResponseMutableLiveData<List<String>>() }
    val keywordsLiveData: ResponseLiveData<List<String>> = _keywordsLiveData
    private val _storeKeywordLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val storeKeywordLiveData: ResponseLiveData<Boolean> = _storeKeywordLiveData

    fun fetchKeywords() {
        _keywordsLiveData requestData { fetchUsecase.toRun(FetchKeywordsUsecase.Request()) }
    }

    fun storeKeyword(keyword: String) {
        _storeKeywordLiveData requestData { addUsecase.toRun(AddKeywordUsecase.Request(KeywordsParams(keyword))) }
    }

    fun removeKeyword(keyword: String) {
    }
}
