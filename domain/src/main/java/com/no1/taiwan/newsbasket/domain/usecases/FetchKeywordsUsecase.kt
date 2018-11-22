package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.queries.NewsQueries
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchKeywordsUsecase.Request
import kotlinx.coroutines.CoroutineScope

class FetchKeywordsUsecase(
    private val repository: DataRepository
) : DeferredUsecase<List<String>, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.fetchKeywords(it.parameters, this).await()
    }

    class Request(val parameters: NewsQueries = NewsQueries()) : RequestValues
}
