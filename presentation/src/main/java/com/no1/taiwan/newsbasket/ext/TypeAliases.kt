package com.no1.taiwan.newsbasket.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.NewsResponse

typealias ResponseMutableLiveData<D> = MutableLiveData<NewsResponse<D>>
typealias ResponseLiveData<D> = LiveData<NewsResponse<D>>

typealias ObservableCaseWithResponse<D, V> = DeferredUsecase<D, V>
typealias SingleCaseWithResponse<D, V> = DeferredUsecase<D, V>
