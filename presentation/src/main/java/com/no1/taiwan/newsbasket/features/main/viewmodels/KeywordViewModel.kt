package com.no1.taiwan.newsbasket.features.main.viewmodels

import androidx.lifecycle.ViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordReq
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordReq
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsReq
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsRespCase
import com.no1.taiwan.newsbasket.ext.RespLiveData
import com.no1.taiwan.newsbasket.ext.RespMutableLiveData
import com.no1.taiwan.newsbasket.ext.exec
import com.no1.taiwan.newsbasket.ext.reqData

class KeywordViewModel(
    private val fetchLocalCase: FetchLocalKeywordsRespCase,
    private val updateRemoteCase: UpdateRemoteKeywordsRespCase,
    private val addKeywordCase: AddKeywordRespUsecase,
    private val mix: DeleteKeywordRespUsecase
) : ViewModel() {
    private val _keywordsLiveData by lazy { RespMutableLiveData<List<String>>() }
    val keywordsLiveData: RespLiveData<List<String>> = _keywordsLiveData
    private val _storeResLiveData by lazy { RespMutableLiveData<Boolean>() }
    val storeResLiveData: RespLiveData<Boolean> = _storeResLiveData
    private val _removeResLiveData by lazy { RespMutableLiveData<Boolean>() }
    val removeResLiveData: RespLiveData<Boolean> = _removeResLiveData

    fun fetchLocalKeywords() = _keywordsLiveData reqData {
        fetchLocalCase.execute()
        fetchLocalCase.exec(FetchLocalKeywordsReq())
    }

    fun storeKeyword(keyword: String) = _storeResLiveData reqData {
        addKeywordCase.exec(AddKeywordReq(KeywordsParams(keyword)))
    }

    fun removeKeyword(keyword: String) = _removeResLiveData reqData {
        mix.exec(DeleteKeywordReq(KeywordsParams(keyword)))
    }
}
