package com.no1.taiwan.newsbasket.data.datas.mappers

import com.no1.taiwan.newsbasket.data.datas.DataSourceMapper
import com.no1.taiwan.newsbasket.data.datas.GoogleNewsSourceData
import com.no1.taiwan.newsbasket.domain.models.NewsSourceModel

/**
 * A transforming mapping between [GoogleNewsSourceData] and [NewsSourceModel]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class SourceMapper : DataSourceMapper {
    override fun toModelFrom(data: GoogleNewsSourceData) = data.run {
        NewsSourceModel(category, country, description, id.orEmpty(), language, name, url)
    }

    override fun toDataFrom(model: NewsSourceModel) = model.run {
        GoogleNewsSourceData(category, country, description, id, language, name, url)
    }
}
