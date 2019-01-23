package com.no1.taiwan.newsbasket.features.main.viewmodels

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordRequest
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordRequest
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsRequest
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun

class KeywordViewModel(
    private val fetchLocalCase: FetchLocalKeywordsWrapUsecase,
    private val updateRemoteCase: UpdateRemoteKeywordsWrapUsecase,
    private val addKeywordCase: AddKeywordRespUsecase,
    private val mix: DeleteKeywordRespUsecase
) : AutoViewModel() {
    private val _keywordsLiveData by lazy { ResponseMutableLiveData<List<String>>() }
    val keywordsLiveData: ResponseLiveData<List<String>> = _keywordsLiveData
    private val _storeResLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val storeResLiveData: ResponseLiveData<Boolean> = _storeResLiveData
    private val _removeResLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val removeResLiveData: ResponseLiveData<Boolean> = _removeResLiveData

    fun fetchLocalKeywords() {
        _keywordsLiveData requestData {
            fetchLocalCase.execute()
            fetchLocalCase.toRun(FetchLocalKeywordsRequest())
        }
    }

    fun storeKeyword(keyword: String) {
        _storeResLiveData requestData { addKeywordCase.toRun(AddKeywordRequest(KeywordsParams(keyword))) }
    }

    fun removeKeyword(keyword: String) {
        _removeResLiveData requestData { mix.toRun(DeleteKeywordRequest(KeywordsParams(keyword))) }
    }
}
