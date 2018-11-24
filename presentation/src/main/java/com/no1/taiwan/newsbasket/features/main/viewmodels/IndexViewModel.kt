package com.no1.taiwan.newsbasket.features.main.viewmodels

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberUsecase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenUsecase
import com.no1.taiwan.newsbasket.entities.PresentationTokenMapper
import com.no1.taiwan.newsbasket.entities.TokenEntity
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun

class IndexViewModel(
    private val addSubscriberUsecase: AddSubscriberUsecase,
    private val tokenMapper: PresentationTokenMapper,
    private val keepNewsTokenUsecase: KeepNewsTokenUsecase
) : AutoViewModel() {
    private val _tokenLiveData by lazy { ResponseMutableLiveData<TokenEntity>() }
    val tokenLiveData: ResponseLiveData<TokenEntity> = _tokenLiveData
    private val _resLiveData by lazy { ResponseMutableLiveData<Boolean>() }
//    val resLiveData: ResponseLiveData<Boolean> = _resLiveData

    fun addSubscriber() {
        _tokenLiveData requestData {
            addSubscriberUsecase.toRun(tokenMapper, AddSubscriberUsecase.Request())
        }
    }

    fun keepNewsToken(entity: TokenEntity) {
        _resLiveData requestData {
            keepNewsTokenUsecase.toRun(KeepNewsTokenUsecase.Request(TokenParams(entity.token, entity.firebaseToken)))
        }
    }
}
