package com.no1.taiwan.newsbasket.entities.mappers

import com.no1.taiwan.newsbasket.domain.models.NewsArticleModel
import com.no1.taiwan.newsbasket.entities.NewsArticleEntity
import com.no1.taiwan.newsbasket.entities.PresentationArticleMapper
import com.no1.taiwan.newsbasket.entities.PresentationSourceMapper

/**
 * A transforming mapping between [NewsArticleModel] and [NewsArticleEntity]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class NewsArticleEntityMapper(
    private val sourceMapper: PresentationSourceMapper
) : PresentationArticleMapper {
    override fun toEntityFrom(model: NewsArticleModel) = model.run {
        NewsArticleEntity(author,
                          content,
                          description,
                          publishedAt,
                          sourceMapper.toEntityFrom(source),
                          title,
                          url,
                          urlToImage)
    }

    override fun toModelFrom(entity: NewsArticleEntity) = entity.run {
        NewsArticleModel(author,
                         content,
                         description,
                         publishedAt,
                         sourceMapper.toModelFrom(source),
                         title,
                         url,
                         urlToImage)
    }
}
