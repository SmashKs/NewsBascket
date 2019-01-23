package com.no1.taiwan.newsbasket.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.NewsResponse

typealias RespMutableLiveData<D> = MutableLiveData<NewsResponse<D>>
typealias RespLiveData<D> = LiveData<NewsResponse<D>>

typealias AsyncCaseWithResp<D, V> = DeferredWrapUsecase<D, V>
