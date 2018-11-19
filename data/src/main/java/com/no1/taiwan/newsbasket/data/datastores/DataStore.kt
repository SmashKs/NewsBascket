package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.datas.NewsesData
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import kotlinx.coroutines.Deferred

interface DataStore {
    fun retrieveNewsData(parameters: Parameterable): Deferred<NewsesData>

    fun createSubscriber(parameters: Parameterable): Deferred<TokenData>

    fun updateKeywords(parameters: Parameterable): Deferred<TokenData>
}
