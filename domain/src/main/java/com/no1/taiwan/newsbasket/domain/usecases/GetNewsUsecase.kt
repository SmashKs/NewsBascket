package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.models.NewsModel
import com.no1.taiwan.newsbasket.domain.parameters.queries.NewsQuerys
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.GetNewsUsecase.Request
import kotlinx.coroutines.CoroutineScope

class GetNewsUsecase(
    private val repository: DataRepository
) : DeferredUsecase<List<NewsModel>, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.fetchNews(it.parameters, this).await()
    }

    class Request(val parameters: NewsQuerys = NewsQuerys()) : RequestValues
}
