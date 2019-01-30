package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.Articles
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.Sources
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.usecases.adblock.FetchAdBlockListRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchEverythingRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchNewsSourcesRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.AddLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.DeleteLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchRemoteNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.token.FetchLocalFirebaseTokenRespCase
import com.no1.taiwan.newsbasket.domain.usecases.token.KeepNewsTokenRespCase

//region 🔽 Aliases of CaseType Interface
typealias AddSubscriberCase = DeferredUsecase<TokenModel, AddSubscriberReq>

typealias FetchLocalKeywordsCase = DeferredUsecase<List<String>, FetchLocalKeywordsReq>
typealias AddKeywordCase = DeferredUsecase<Boolean, AddKeywordReq>
typealias AddLocalKeywordCase = DeferredUsecase<Boolean, AddLocalKeywordReq>
typealias DeleteKeywordCase = DeferredUsecase<Boolean, DeleteKeywordReq>
typealias DeleteLocalKeywordCase = DeferredUsecase<Boolean, DeleteLocalKeywordReq>
typealias UpdateRemoteKeywordsCase = DeferredUsecase<Boolean, UpdateRemoteKeywordsReq>

typealias AddLocalNewsCase = DeferredUsecase<Boolean, AddLocalNewsReq>
typealias DeleteLocalNewsCase = DeferredUsecase<Boolean, DeleteLocalNewsReq>
typealias FetchLocalNewsCase = DeferredUsecase<Newses, FetchLocalNewsReq>
typealias FetchRemoteNewsCase = DeferredUsecase<Newses, FetchRemoteNewsReq>

typealias FetchLocalFirebaseTokenCase = DeferredUsecase<List<String>, FetchLocalFirebaseTokenReq>
typealias KeepNewsTokenCase = DeferredUsecase<Boolean, KeepNewsTokenReq>

typealias FetchTopNewsCase = DeferredUsecase<Articles, FetchTopNewsReq>
typealias FetchEverythingCase = DeferredUsecase<Articles, FetchEverythingReq>
typealias FetchNewsSourcesCase = DeferredUsecase<Sources, FetchNewsSourcesReq>

typealias FetchAdBlockListCase = DeferredUsecase<List<String>, FetchAdBlockListReq>
//endregion

//region 🔽 Aliases of Request
typealias AddSubscriberReq = AddSubscriberRespCase.Request

typealias FetchLocalKeywordsReq = FetchLocalKeywordsRespCase.Request
typealias AddKeywordReq = AddKeywordRespCase.Request
typealias AddLocalKeywordReq = AddLocalKeywordRespCase.Request
typealias DeleteKeywordReq = DeleteKeywordRespCase.Request
typealias DeleteLocalKeywordReq = DeleteLocalKeywordRespCase.Request
typealias UpdateRemoteKeywordsReq = UpdateRemoteKeywordsRespCase.Request

typealias AddLocalNewsReq = AddLocalNewsRespCase.Request
typealias DeleteLocalNewsReq = DeleteLocalNewsRespCase.Request
typealias FetchLocalNewsReq = FetchLocalNewsRespCase.Request
typealias FetchRemoteNewsReq = FetchRemoteNewsRespCase.Request

typealias FetchLocalFirebaseTokenReq = FetchLocalFirebaseTokenRespCase.Request
typealias KeepNewsTokenReq = KeepNewsTokenRespCase.Request

typealias FetchTopNewsReq = FetchTopNewsRespCase.Request
typealias FetchEverythingReq = FetchEverythingRespCase.Request
typealias FetchNewsSourcesReq = FetchNewsSourcesRespCase.Request

typealias FetchAdBlockListReq = FetchAdBlockListRespCase.Request
//endregion
