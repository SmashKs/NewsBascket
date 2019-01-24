@file:Suppress("NOTHING_TO_INLINE")

package com.no1.taiwan.newsbasket.ext

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.models.Model
import com.no1.taiwan.newsbasket.entities.Entity
import com.no1.taiwan.newsbasket.entities.mappers.Mapper

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredUsecase] and execute the usecase work without
 * the mapper (Because the variables should be primitive variable).
 *
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Any, V : RequestValues> AsyncCase<M, V>.exec(
    parameter: V? = null
) = execute(parameter)

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredUsecase] and execute the usecase work with
 * the mapper for transform to an object for presentation layer. (Because the variables should be
 * primitive variable).
 *
 * @param mapper the mapper for transforming from [Model] to [Entity].
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues> AsyncCase<M, V>.execMapping(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = execute(parameter).run(mapper::toEntityFrom)

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredUsecase] and execute the usecase work with
 * the mapper for transform to a list of object for presentation layer. (Because the variables should be
 * primitive variable).
 *
 * @param mapper the mapper for transforming from List<[Model]> to List<[Entity]>.
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues, MS : List<M>> AsyncCase<MS, V>.execListMapping(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = execute(parameter).map(mapper::toEntityFrom)
