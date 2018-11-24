package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.datas.NewsesData
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import kotlinx.coroutines.Deferred

/**
 * This interface will common the all data stores.
 * Using prefix name (retrieve), (create), (modify), (remove), (store)
 */
interface DataStore {
    fun retrieveNewsData(parameters: Parameterable): Deferred<NewsesData>

    fun createSubscriber(parameters: Parameterable): Deferred<TokenData>

    fun modifyKeywords(parameters: Parameterable): Deferred<TokenData>

    fun storeNewsToken(parameters: Parameterable): Deferred<Boolean>

    fun retrieveFirebaseToken(): Deferred<String>

    fun retrieveToken(): Deferred<String>

    fun retrieveKeywords(): Deferred<List<String>>

    fun createKeyword(parameters: Parameterable): Deferred<Boolean>

    fun removeKeyword(parameters: Parameterable): Deferred<Boolean>
}
