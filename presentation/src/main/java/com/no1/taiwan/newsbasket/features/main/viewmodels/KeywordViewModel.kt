package com.no1.taiwan.newsbasket.features.main.viewmodels

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalKeywordWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun

class KeywordViewModel(
    private val fetchLocalUsecase: FetchLocalKeywordsWrapUsecase,
    private val addLocalUsecase: AddLocalKeywordWrapUsecase,
    private val deleteLocalUsecase: DeleteLocalKeywordWrapUsecase,
    private val updateRemoteRemoteUsecase: UpdateRemoteKeywordsWrapUsecase,
    private val mix: AddKeywordRespUsecase
) : AutoViewModel() {
    private val _keywordsLiveData by lazy { ResponseMutableLiveData<List<String>>() }
    val keywordsLiveData: ResponseLiveData<List<String>> = _keywordsLiveData
    private val _storeKeywordLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val storeKeywordLiveData: ResponseLiveData<Boolean> = _storeKeywordLiveData
    private val _updateKeywordsLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val updateKeywordsLiveData: ResponseLiveData<Boolean> = _updateKeywordsLiveData
    private val _removeKeywordLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val removeKeywordLiveData: ResponseLiveData<Boolean> = _removeKeywordLiveData

    fun fetchLocalKeywords() {
        _keywordsLiveData requestData { fetchLocalUsecase.toRun(FetchLocalKeywordsWrapUsecase.Request()) }
    }

    fun storeLocalKeyword(keyword: String) {
        _storeKeywordLiveData requestData {
            addLocalUsecase.toRun(AddLocalKeywordWrapUsecase.Request(KeywordsParams(keyword)))
        }
    }

    fun updateRemoteSubscribing(keywords: String) {
        _updateKeywordsLiveData requestData {
            updateRemoteRemoteUsecase.toRun(UpdateRemoteKeywordsWrapUsecase.Request(KeywordsFields(keywords = keywords)))
        }
    }

    fun removeKeyword(keyword: String) {
        _removeKeywordLiveData requestData {
            val res = deleteLocalUsecase.toRun(DeleteLocalKeywordWrapUsecase.Request(KeywordsParams(keyword)))
            if (res.data == true) {
                updateRemoteRemoteUsecase.toRun(UpdateRemoteKeywordsWrapUsecase.Request())
            }
            else {
                res
            }
        }
    }

    fun mix(keyword: String) {
        _storeKeywordLiveData requestData { mix.toRun(AddKeywordRespUsecase.Request(KeywordsParams(keyword))) }
    }
}
