package com.no1.taiwan.newsbasket.features.main.viewmodels

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsUsecase
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsUsecase
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun

class KeywordViewModel(
    private val fetchLocalUsecase: FetchLocalKeywordsUsecase,
    private val addLocalUsecase: AddLocalKeywordUsecase,
    private val deleteLocalUsecase: DeleteLocalKeywordUsecase,
    private val updateRemoteRemoteUsecase: UpdateRemoteKeywordsUsecase
) : AutoViewModel() {
    private val _keywordsLiveData by lazy { ResponseMutableLiveData<List<String>>() }
    val keywordsLiveData: ResponseLiveData<List<String>> = _keywordsLiveData
    private val _storeKeywordLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val storeKeywordLiveData: ResponseLiveData<Boolean> = _storeKeywordLiveData
    private val _updateKeywordsLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val updateKeywordsLiveData: ResponseLiveData<Boolean> = _storeKeywordLiveData
    private val _removeKeywordLiveData by lazy { ResponseMutableLiveData<Boolean>() }
    val removeKeywordLiveData: ResponseLiveData<Boolean> = _removeKeywordLiveData

    fun fetchLocalKeywords() {
        _keywordsLiveData requestData { fetchLocalUsecase.toRun(FetchLocalKeywordsUsecase.Request()) }
    }

    fun storeLocalKeyword(keyword: String) {
        _storeKeywordLiveData requestData { addLocalUsecase.toRun(AddLocalKeywordUsecase.Request(KeywordsParams(keyword))) }
    }

    fun updateRemoteSubscribing(keywords: String) {
        _updateKeywordsLiveData requestData {
            updateRemoteRemoteUsecase.toRun(UpdateRemoteKeywordsUsecase.Request(KeywordsFields(keywords = keywords)))
        }
    }

    fun removeKeyword(keyword: String) {
        _removeKeywordLiveData requestData {
            val res = deleteLocalUsecase.toRun(DeleteLocalKeywordUsecase.Request(KeywordsParams(keyword)))
            if (res.data == true) {
                updateRemoteRemoteUsecase.toRun(UpdateRemoteKeywordsUsecase.Request())
            }
            else {
                res
            }
        }
    }
}
