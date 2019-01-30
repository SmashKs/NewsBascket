package com.no1.taiwan.newsbasket.domain.usecases.adblock

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.repositories.AdBlockRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchAdBlockListCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchAdBlockListReq

class FetchAdBlockListRespCase(
    private val adBlockRepo: AdBlockRepository
) : FetchAdBlockListCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: FetchAdBlockListReq? = FetchAdBlockListReq()

    override suspend fun acquireCase() = attachParameter {
        adBlockRepo.fetchAdBlockList()
    }

    class Request : RequestValues
}
