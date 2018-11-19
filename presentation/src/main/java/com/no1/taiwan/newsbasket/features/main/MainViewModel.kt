package com.no1.taiwan.newsbasket.features.main

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberUsecase
import com.no1.taiwan.newsbasket.domain.usecases.GetNewsUsecase
import com.no1.taiwan.newsbasket.entities.Keywords
import com.no1.taiwan.newsbasket.entities.Newses
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper
import com.no1.taiwan.newsbasket.entities.PresentationTokenMapper
import com.no1.taiwan.newsbasket.entities.TokenEntity
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.const.Constants
import com.no1.taiwan.newsbasket.ext.const.Token
import com.no1.taiwan.newsbasket.ext.mmkv.kvToken
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun
import com.no1.taiwan.newsbasket.ext.toRunList

class MainViewModel(
    private val usecase: GetNewsUsecase,
    private val mapper: PresentationNewsMapper,
    private val uc1: AddSubscriberUsecase,
    private val m1: PresentationTokenMapper
) : AutoViewModel() {
    private val _newsLiveData by lazy { ResponseMutableLiveData<Newses>() }
    val newsLiveData: ResponseLiveData<Newses> = _newsLiveData
    private val _tokenLiveData by lazy { ResponseMutableLiveData<TokenEntity>() }
    val tokenLiveData: ResponseLiveData<Boolean> = ResponseMutableLiveData()

    fun fetchNews() = _newsLiveData requestData { usecase.toRunList(mapper, GetNewsUsecase.Request()) }

    fun addSubscriber(token: Token, keywords: Keywords) {
        val strKeyword = keywords.joinToString(",")
        _tokenLiveData requestData { uc1.toRun(m1, AddSubscriberUsecase.Request(SubscriberFields(token, strKeyword))) }
    }

    fun keepToken(tokenEntity: TokenEntity) {
        kvToken.encode(Constants.MmkvKey.TOKEN, tokenEntity.token)
    }
}
