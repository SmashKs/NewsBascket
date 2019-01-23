package com.no1.taiwan.newsbasket.features.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devrapid.kotlinshaver.cast
import com.devrapid.kotlinshaver.gLaunch
import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.NewsParameter.Country.JP
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberReq
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberRespCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsReq
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenReq
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsRespCase
import com.no1.taiwan.newsbasket.entities.Articles
import com.no1.taiwan.newsbasket.entities.PresentationMapperPool
import com.no1.taiwan.newsbasket.entities.TokenEntity
import com.no1.taiwan.newsbasket.entities.mappers.NewsArticleEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.TokenEntityMapper
import com.no1.taiwan.newsbasket.ext.RespLiveData
import com.no1.taiwan.newsbasket.ext.RespMutableLiveData
import com.no1.taiwan.newsbasket.ext.const.Token
import com.no1.taiwan.newsbasket.ext.reqData
import com.no1.taiwan.newsbasket.ext.toRun
import com.no1.taiwan.newsbasket.ext.toRunList

class IndexViewModel(
    private val addSubscriberUsecase: AddSubscriberRespCase,
    private val keepNewsTokenRespCase: KeepNewsTokenRespCase,
    private val fetchTopNewsRespCase: FetchTopNewsRespCase,
    private val mapperPool: PresentationMapperPool
) : AutoViewModel() {
    private val _tokenLiveData by lazy { RespMutableLiveData<TokenEntity>() }
    val tokenLiveData: RespLiveData<TokenEntity> = _tokenLiveData
    private val _resLiveData by lazy { MutableLiveData<Boolean>() }
    val resLiveData: LiveData<Boolean> = _resLiveData
    private val _topNewses by lazy { RespMutableLiveData<Articles>() }
    val topNewses: RespLiveData<Articles> = _topNewses
    private val tokenMapper by lazy { cast<TokenEntityMapper>(mapperPool[TokenEntityMapper::class.java]) }
    private val articleMapper by lazy { cast<NewsArticleEntityMapper>(mapperPool[NewsArticleEntityMapper::class.java]) }

    fun addFirstSubscriber(firebaseToken: Token) = _tokenLiveData reqData {
        addSubscriberUsecase.toRun(tokenMapper, AddSubscriberReq(SubscriberFields(firebaseToken)))
    }

    fun keepNewsToken(entity: TokenEntity) = gLaunch {
        _resLiveData.postValue(keepNewsTokenRespCase.execute(KeepNewsTokenReq(TokenParams(entity.token,
                                                                                          entity.firebaseToken))))
    }

    fun fetchTopNews() = _topNewses reqData {
        fetchTopNewsRespCase.toRunList(articleMapper, FetchTopNewsReq(TopParams(country = JP)))
    }

    fun test() {
        val d = MutableLiveData<Any>()
        gLaunch {
            val a = fetchTopNewsRespCase.execute()
            d.postValue(a)
        }
    }
}
