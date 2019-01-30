package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.datas.GoogleNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.GoogleNewsSourceInfoData
import com.no1.taiwan.newsbasket.data.datas.RemoteNewsInfoData
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable

/**
 * This interface will common the all data stores.
 * Using prefix name (get), (create), (modify), (remove), (store)
 */
interface DataStore {
    //region Google News Service
    suspend fun getTopNews(parameters: Parameterable): GoogleNewsInfoData

    suspend fun getEverythingNews(parameters: Parameterable): GoogleNewsInfoData

    suspend fun getNewsSources(parameters: Parameterable): GoogleNewsSourceInfoData
    //endregion

    //region Keeping news into local device & Subscribe the specific news by keyword
    suspend fun getNewsData(parameters: Parameterable): RemoteNewsInfoData

    suspend fun createNews(parameters: Parameterable): Boolean

    suspend fun removeNews(parameters: Parameterable): Boolean

    suspend fun createSubscriber(parameters: Parameterable): TokenData

    suspend fun modifyKeywords(parameters: Parameterable): TokenData
    //endregion

    //region Register for Token
    suspend fun storeNewsToken(parameters: Parameterable): Boolean

    suspend fun getFirebaseToken(): String

    suspend fun getToken(): String
    //endregion

    //region Keyword
    suspend fun getKeywords(): List<String>

    suspend fun createKeyword(parameters: Parameterable): Boolean

    suspend fun removeKeyword(parameters: Parameterable, transactionBlock: (() -> Boolean)? = null): Boolean
    //endregion

    suspend fun getBlockList(): List<String>
}
