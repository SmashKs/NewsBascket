package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.parameters.queries.NewsQueries
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchNewsUsecase.Request
import kotlinx.coroutines.CoroutineScope

class FetchNewsUsecase(
    private val repository: DataRepository
) : DeferredUsecase<Newses, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.fetchNews(it.parameters, this).await()
    }

    class Request(val parameters: NewsQueries = NewsQueries()) : RequestValues
}
