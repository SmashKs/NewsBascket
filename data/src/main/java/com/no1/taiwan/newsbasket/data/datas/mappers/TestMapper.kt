package com.no1.taiwan.newsbasket.data.datas.mappers

import com.no1.taiwan.newsbasket.data.datas.DataTestMapper
import com.no1.taiwan.newsbasket.data.datas.TestData
import com.no1.taiwan.newsbasket.domain.models.TestModel

/**
 * A transforming mapping between [TestData] and [TestModel]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class TestMapper : DataTestMapper {
    override fun toModelFrom(data: TestData) = data.run {
        TestModel(items.map { item ->
            TestModel.ItemModel(item.id,
                                item.itemState,
                                item.itemStateName,
                                item.planId,
                                item.planName,
                                item.brandId,
                                item.brandName,
                                item.brandKana,
                                item.name,
                                item.sex,
                                item.priorityReserveItemId,
                                item.partnershipCompanyId,
                                item.favorite,
                                item.imageFile.map {
                                    TestModel.ImageFileModel(it.url, it.thumbUrl, it.largeUrl)
                                })
        }, quantity, res)
    }

    override fun toDataFrom(model: TestModel) = model.run {
        TestData(items.map { item ->
            TestData.ItemData(item.id,
                              item.itemState,
                              item.itemStateName,
                              item.planId,
                              item.planName,
                              item.brandId,
                              item.brandName,
                              item.brandKana,
                              item.name,
                              item.sex,
                              item.priorityReserveItemId,
                              item.partnershipCompanyId,
                              item.favorite,
                              item.imageFile.map {
                                  TestData.ImageFileData(it.url, it.thumbUrl, it.largeUrl)
                              })
        }, quantity, res)
    }
}
