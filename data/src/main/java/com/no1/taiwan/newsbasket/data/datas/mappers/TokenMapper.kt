package com.no1.taiwan.newsbasket.data.datas.mappers

import com.no1.taiwan.newsbasket.data.datas.DataTokenMapper
import com.no1.taiwan.newsbasket.data.datas.TokenData
import com.no1.taiwan.newsbasket.domain.models.TokenModel

/**
 * A transforming mapping between [TokenData] and [TokenModel]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class TokenMapper : DataTokenMapper {
    override fun toModelFrom(data: TokenData) = data.run {
        TokenModel(token, firebaseToken)
    }

    override fun toDataFrom(model: TokenModel) = model.run {
        TokenData(token, firebaseToken)
    }
}
