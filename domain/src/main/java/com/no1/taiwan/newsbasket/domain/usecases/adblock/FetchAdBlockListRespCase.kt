package com.no1.taiwan.newsbasket.domain.usecases.adblock

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.EmptyParams
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.AdBlockRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchAdBlockListCase

class FetchAdBlockListRespCase(
    private val repository: AdBlockRepository,
    override var requestValues: Request? = null
) : FetchAdBlockListCase() {
    override suspend fun acquireCase() = attachParameter {
        repository.fetchAdBlockList()
    }

    class Request(val parameters: Parameterable = EmptyParams()) : RequestValues
}
