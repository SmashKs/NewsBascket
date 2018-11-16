package com.no1.taiwan.newsbasket.ext

import androidx.lifecycle.MutableLiveData
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.NewsResponse

typealias ResponseLiveData<D> = MutableLiveData<NewsResponse<D>>

typealias ObservableCaseWithResponse<D, V> = DeferredUsecase<D, V>
typealias SingleCaseWithResponse<D, V> = DeferredUsecase<D, V>
