package com.no1.taiwan.newsbasket.features.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devrapid.kotlinshaver.ui
import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.NewsParameter.Country.JP
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberRequest
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsRequest
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenRequest
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsWrapUsecase
import com.no1.taiwan.newsbasket.entities.Articles
import com.no1.taiwan.newsbasket.entities.PresentationArticleMapper
import com.no1.taiwan.newsbasket.entities.PresentationTokenMapper
import com.no1.taiwan.newsbasket.entities.TokenEntity
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.const.Token
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun
import com.no1.taiwan.newsbasket.ext.toRunList

class IndexViewModel(
    private val addSubscriberUsecase: AddSubscriberWrapUsecase,
    private val tokenMapper: PresentationTokenMapper,
    private val keepNewsTokenWrapUsecase: KeepNewsTokenWrapUsecase,
    private val fetchTopNewsWrapUsecase: FetchTopNewsWrapUsecase,
    private val articleMapper: PresentationArticleMapper
) : AutoViewModel() {
    private val _tokenLiveData by lazy { ResponseMutableLiveData<TokenEntity>() }
    val tokenLiveData: ResponseLiveData<TokenEntity> = _tokenLiveData
    private val _resLiveData by lazy { MutableLiveData<Boolean>() }
    val resLiveData: LiveData<Boolean> = _resLiveData
    private val _topNewses by lazy { ResponseMutableLiveData<Articles>() }
    val topNewses: ResponseLiveData<Articles> = _topNewses

    fun addFirstSubscriber(firebaseToken: Token) {
        _tokenLiveData requestData {
            addSubscriberUsecase.toRun(tokenMapper, AddSubscriberRequest(SubscriberFields(firebaseToken)))
        }
    }

    fun keepNewsToken(entity: TokenEntity) {
        ui {
            _resLiveData.value = keepNewsTokenWrapUsecase
                .execute(KeepNewsTokenRequest(TokenParams(entity.token, entity.firebaseToken)))
        }
    }

    fun fetchTopNews() {
        _topNewses requestData {
            fetchTopNewsWrapUsecase.toRunList(articleMapper, FetchTopNewsRequest(TopParams(country = JP)))
        }
    }
}
