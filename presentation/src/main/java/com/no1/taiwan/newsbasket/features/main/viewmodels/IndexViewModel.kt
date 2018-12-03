package com.no1.taiwan.newsbasket.features.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devrapid.kotlinknifer.ui
import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenUsecase
import com.no1.taiwan.newsbasket.entities.PresentationTokenMapper
import com.no1.taiwan.newsbasket.entities.TokenEntity
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.const.Token
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberWrapUsecase.Request as AddSubscriberRequest
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenUsecase.Request as KeepNewsTokenRequest

class IndexViewModel(
    private val addSubscriberUsecase: AddSubscriberWrapUsecase,
    private val tokenMapper: PresentationTokenMapper,
    private val keepNewsTokenUsecase: KeepNewsTokenUsecase
) : AutoViewModel() {
    private val _tokenLiveData by lazy { ResponseMutableLiveData<TokenEntity>() }
    val tokenLiveData: ResponseLiveData<TokenEntity> = _tokenLiveData
    private val _resLiveData by lazy { MutableLiveData<Boolean>() }
    val resLiveData: LiveData<Boolean> = _resLiveData

    fun addFirstSubscriber(firebaseToken: Token) {
        _tokenLiveData requestData {
            addSubscriberUsecase.toRun(tokenMapper, AddSubscriberRequest(SubscriberFields(firebaseToken)))
        }
    }

    fun keepNewsToken(entity: TokenEntity) {
        ui {
            _resLiveData.value = keepNewsTokenUsecase
                .execute(KeepNewsTokenRequest(TokenParams(entity.token, entity.firebaseToken)))
        }
    }
}
