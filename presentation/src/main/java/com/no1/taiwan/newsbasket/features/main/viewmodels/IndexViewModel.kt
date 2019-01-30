package com.no1.taiwan.newsbasket.features.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devrapid.kotlinshaver.cast
import com.devrapid.kotlinshaver.gLaunch
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.NewsParameter.Country.JP
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberCase
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberReq
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsReq
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenCase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenReq
import com.no1.taiwan.newsbasket.entities.Articles
import com.no1.taiwan.newsbasket.entities.PresentationMapperPool
import com.no1.taiwan.newsbasket.entities.TokenEntity
import com.no1.taiwan.newsbasket.entities.mappers.NewsArticleEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.TokenEntityMapper
import com.no1.taiwan.newsbasket.ext.RespLiveData
import com.no1.taiwan.newsbasket.ext.RespMutableLiveData
import com.no1.taiwan.newsbasket.ext.const.Token
import com.no1.taiwan.newsbasket.ext.execListMapping
import com.no1.taiwan.newsbasket.ext.execMapping
import com.no1.taiwan.newsbasket.ext.reqData

class IndexViewModel(
    private val addSubscriberCase: AddSubscriberCase,
    private val keepNewsTokenCase: KeepNewsTokenCase,
    private val fetchTopNewsCase: FetchTopNewsCase,
    private val mapperPool: PresentationMapperPool
) : ViewModel() {
    private val _tokenLiveData by lazy { RespMutableLiveData<TokenEntity>() }
    val tokenLiveData: RespLiveData<TokenEntity> = _tokenLiveData
    private val _resLiveData by lazy { MutableLiveData<Boolean>() }
    val resLiveData: LiveData<Boolean> = _resLiveData
    private val _topNewses by lazy { RespMutableLiveData<Articles>() }
    val topNewses: RespLiveData<Articles> = _topNewses
    private val tokenMapper by lazy { cast<TokenEntityMapper>(mapperPool[TokenEntityMapper::class.java]) }
    private val articleMapper by lazy { cast<NewsArticleEntityMapper>(mapperPool[NewsArticleEntityMapper::class.java]) }

    fun addFirstSubscriber(firebaseToken: Token) = _tokenLiveData reqData {
        addSubscriberCase.execMapping(tokenMapper, AddSubscriberReq(SubscriberFields(firebaseToken)))
    }

    fun keepNewsToken(entity: TokenEntity) = gLaunch {
        _resLiveData.postValue(keepNewsTokenCase.execute(KeepNewsTokenReq(TokenParams(entity.token,
                                                                                      entity.firebaseToken))))
    }

    fun fetchTopNews() = _topNewses reqData {
        fetchTopNewsCase.execListMapping(articleMapper, FetchTopNewsReq(TopParams(country = JP)))
    }
}
