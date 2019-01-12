package com.no1.taiwan.newsbasket.data.datas.mappers

import com.no1.taiwan.newsbasket.data.datas.DataArticleMapper
import com.no1.taiwan.newsbasket.data.datas.DataSourceMapper
import com.no1.taiwan.newsbasket.data.datas.GoogleNewsArticleData
import com.no1.taiwan.newsbasket.domain.models.GoogleNewsArticleModel

/**
 * A transforming mapping between [GoogleNewsArticleData] and [GoogleNewsArticleModel]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class ArticleMapper(
    private val sourceMapper: DataSourceMapper
) : DataArticleMapper {
    override fun toModelFrom(data: GoogleNewsArticleData) = data.run {
        GoogleNewsArticleModel(author.orEmpty(),
                               content.orEmpty(),
                               description,
                               publishedAt,
                               sourceMapper.toModelFrom(source),
                               title,
                               url,
                               urlToImage)
    }

    override fun toDataFrom(model: GoogleNewsArticleModel) = model.run {
        GoogleNewsArticleData(author,
                              content,
                              description,
                              publishedAt,
                              sourceMapper.toDataFrom(source),
                              title,
                              url,
                              urlToImage)
    }
}
