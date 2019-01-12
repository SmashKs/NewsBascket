package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.datas.GoogleNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.GoogleNewsSourceInfoData
import com.no1.taiwan.newsbasket.data.datas.RemoteNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import kotlinx.coroutines.Deferred

/**
 * This interface will common the all data stores.
 * Using prefix name (retrieve), (create), (modify), (remove), (store)
 */
interface DataStore {
    //region Google News Service
    fun retrieveTopNews(parameters: Parameterable): Deferred<GoogleNewsInfoData>

    fun retrieveEverythingNews(parameters: Parameterable): Deferred<GoogleNewsInfoData>

    fun retrieveNewsSources(parameters: Parameterable): Deferred<GoogleNewsSourceInfoData>
    //endregion

    //region Keeping news into local device & Subscribe the specific news by keyword
    fun retrieveNewsData(parameters: Parameterable): Deferred<RemoteNewsInfoData>

    fun createNews(parameters: Parameterable): Deferred<Boolean>

    fun removeNews(parameters: Parameterable): Deferred<Boolean>

    fun createSubscriber(parameters: Parameterable): Deferred<TokenData>

    fun modifyKeywords(parameters: Parameterable): Deferred<TokenData>
    //endregion

    //region Register for Token
    fun storeNewsToken(parameters: Parameterable): Deferred<Boolean>

    fun retrieveFirebaseToken(): Deferred<String>

    fun retrieveToken(): Deferred<String>
    //endregion

    //region Keyword
    fun retrieveKeywords(): Deferred<List<String>>

    fun createKeyword(parameters: Parameterable): Deferred<Boolean>

    fun removeKeyword(parameters: Parameterable, transactionBlock: (() -> Deferred<Boolean>)? = null): Deferred<Boolean>
    //endregion
}
