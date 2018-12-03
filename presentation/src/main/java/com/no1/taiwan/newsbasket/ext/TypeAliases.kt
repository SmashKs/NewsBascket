package com.no1.taiwan.newsbasket.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.NewsResponse

typealias ResponseMutableLiveData<D> = MutableLiveData<NewsResponse<D>>
typealias ResponseLiveData<D> = LiveData<NewsResponse<D>>

typealias ObservableCaseWithResponse<D, V> = DeferredWrapUsecase<D, V>
typealias SingleCaseWithResponse<D, V> = DeferredWrapUsecase<D, V>
