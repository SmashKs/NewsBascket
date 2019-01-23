package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchEverythingRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchNewsSourcesRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchRemoteNewsRespCase

typealias AddKeywordReq = AddKeywordRespUsecase.Request
typealias AddLocalKeywordReq = AddLocalKeywordRespCase.Request
typealias AddSubscriberReq = AddSubscriberRespCase.Request
typealias DeleteKeywordReq = DeleteKeywordRespUsecase.Request
typealias DeleteLocalKeywordReq = DeleteLocalKeywordRespCase.Request
typealias FetchLocalFirebaseTokenReq = FetchLocalFirebaseTokenRespCase.Request
typealias FetchLocalKeywordsReq = FetchLocalKeywordsRespCase.Request
typealias FetchRemoteNewsReq = FetchRemoteNewsRespCase.Request
typealias UpdateRemoteKeywordsReq = UpdateRemoteKeywordsRespCase.Request
typealias KeepNewsTokenReq = KeepNewsTokenRespCase.Request

typealias FetchTopNewsReq = FetchTopNewsRespCase.Request
typealias FetchEverythingReq = FetchEverythingRespCase.Request
typealias FetchNewsSourcesReq = FetchNewsSourcesRespCase.Request
