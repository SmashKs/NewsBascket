package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.models.TestModel
import com.no1.taiwan.newsbasket.domain.parameters.NewsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.TestUsecase.Request
import kotlinx.coroutines.CoroutineScope

class TestUsecase(
    private val repository: DataRepository
) : DeferredUsecase<TestModel, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.fetchTest(it.parameters, this).await()
    }

    class Request(val parameters: NewsParams = NewsParams()) : RequestValues
}
