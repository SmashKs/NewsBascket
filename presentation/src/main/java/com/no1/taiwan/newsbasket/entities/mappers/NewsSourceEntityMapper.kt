package com.no1.taiwan.newsbasket.entities.mappers

import com.no1.taiwan.newsbasket.domain.models.NewsSourceModel
import com.no1.taiwan.newsbasket.entities.NewsSourceEntity
import com.no1.taiwan.newsbasket.entities.PresentationSourceMapper

/**
 * A transforming mapping between [NewsSourceModel] and [NewsSourceEntity]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class NewsSourceEntityMapper : PresentationSourceMapper {
    override fun toEntityFrom(model: NewsSourceModel) = model.run {
        NewsSourceEntity(category, country, description, id, language, name, url)
    }

    override fun toModelFrom(entity: NewsSourceEntity) = entity.run {
        NewsSourceModel(category, country, description, id, language, name, url)
    }
}
