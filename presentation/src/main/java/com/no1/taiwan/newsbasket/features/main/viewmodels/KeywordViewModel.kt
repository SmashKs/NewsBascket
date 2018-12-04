package com.no1.taiwan.newsbasket.features.main.viewmodels

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun
import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordRespUsecase.Request as AddKeywordRequest
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsWrapUsecase.Request as FetchLocalKeywordsRequest

class KeywordViewModel(
    private val fetchLocalCase: FetchLocalKeywordsWrapUsecase,
    private val updateRemoteCase: UpdateRemoteKeywordsWrapUsecase,
    private val addKeywordCase: AddKeywordRespUsecase
) : AutoViewModel() {
    private val _keywordsLiveData by lazy { ResponseMutableLiveData<List<String>>() }
    val keywordsLiveData: ResponseLiveData<List<String>> = _keywordsLiveData
    private val _storeResLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val storeResLiveData: ResponseLiveData<Boolean> = _storeResLiveData
    private val _removeResLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val removeResLiveData: ResponseLiveData<Boolean> = _removeResLiveData

    fun fetchLocalKeywords() {
        _keywordsLiveData requestData { fetchLocalCase.toRun(FetchLocalKeywordsRequest()) }
    }

    fun removeKeyword(keyword: String) {
//        _removeResLiveData requestData {
//            val res = deleteLocalUsecase.toRun(DeleteLocalKeywordWrapUsecase.Request(KeywordsParams(keyword)))
//            if (res.data == true) {
//                updateRemoteCase.toRun(UpdateRemoteKeywordsWrapUsecase.Request())
//            }
//            else {
//                res
//            }
//        }
    }

    fun storeKeyword(keyword: String) {
        _storeResLiveData requestData { addKeywordCase.toRun(AddKeywordRequest(KeywordsParams(keyword))) }
    }
}
