package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.datas.NewsData
import com.no1.taiwan.newsbasket.data.datas.TestData
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import kotlinx.coroutines.Deferred

interface DataStore {
    fun retrieveTest(parameters: Parameterable): Deferred<TestData>

    fun retrieveNewsData(parameters: Parameterable): Deferred<List<NewsData>>
}
