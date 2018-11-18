package com.no1.taiwan.newsbasket.entities.mappers

import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.entities.PresentationTokenMapper
import com.no1.taiwan.newsbasket.entities.TokenEntity

/**
 * A transforming mapping between [TokenModel] and [TokenEntity]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class TokenEntityMapper : PresentationTokenMapper {
    override fun toEntityFrom(model: TokenModel) = model.run {
        TokenEntity(token, firebaseToken)
    }

    override fun toModelFrom(entity: TokenEntity) = entity.run {
        TokenModel(token, firebaseToken)
    }
}
