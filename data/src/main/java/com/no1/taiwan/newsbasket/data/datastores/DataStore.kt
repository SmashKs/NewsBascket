package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.datas.GoogleNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.GoogleNewsSourceInfoData
import com.no1.taiwan.newsbasket.data.datas.RemoteNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable

/**
 * This interface will common the all data stores.
 * Using prefix name (retrieve), (create), (modify), (remove), (store)
 */
interface DataStore {
    //region Google News Service
    suspend fun retrieveTopNews(parameters: Parameterable): GoogleNewsInfoData

    suspend fun retrieveEverythingNews(parameters: Parameterable): GoogleNewsInfoData

    suspend fun retrieveNewsSources(parameters: Parameterable): GoogleNewsSourceInfoData
    //endregion

    //region Keeping news into local device & Subscribe the specific news by keyword
    suspend fun retrieveNewsData(parameters: Parameterable): RemoteNewsInfoData

    suspend fun createNews(parameters: Parameterable): Boolean

    suspend fun removeNews(parameters: Parameterable): Boolean

    suspend fun createSubscriber(parameters: Parameterable): TokenData

    suspend fun modifyKeywords(parameters: Parameterable): TokenData
    //endregion

    //region Register for Token
    suspend fun storeNewsToken(parameters: Parameterable): Boolean

    suspend fun retrieveFirebaseToken(): String

    suspend fun retrieveToken(): String
    //endregion

    //region Keyword
    suspend fun retrieveKeywords(): List<String>

    suspend fun createKeyword(parameters: Parameterable): Boolean

    suspend fun removeKeyword(parameters: Parameterable, transactionBlock: (() -> Boolean)? = null): Boolean
    //endregion
}
