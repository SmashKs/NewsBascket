package com.no1.taiwan.newsbasket.entities.mappers

import com.no1.taiwan.newsbasket.domain.models.TestModel
import com.no1.taiwan.newsbasket.entities.PresentationTestMapper
import com.no1.taiwan.newsbasket.entities.TestEntity

/**
 * A transforming mapping between [TestModel] and [TestEntity]. The different layers have
 * their own data objects, the objects should transform and fit each layers.
 */
class TestEntityMapper : PresentationTestMapper {
    override fun toEntityFrom(model: TestModel) = model.run {
        TestEntity(items.map { item ->
            TestEntity.ItemEntity(item.id,
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
                                      TestEntity.ImageFileEntity(it.url, it.thumbUrl, it.largeUrl)
                                  })
        }, quantity, res)
    }

    override fun toModelFrom(entity: TestEntity) = entity.run {
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
}
