@file:Suppress("NOTHING_TO_INLINE")

package com.no1.taiwan.newsbasket.ext

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.NewsResponse
import com.no1.taiwan.newsbasket.domain.NewsResponse.Error
import com.no1.taiwan.newsbasket.domain.NewsResponse.Success
import com.no1.taiwan.newsbasket.domain.models.Model
import com.no1.taiwan.newsbasket.entities.Entity
import com.no1.taiwan.newsbasket.entities.mappers.Mapper
import kotlinx.coroutines.Deferred

//region DeferredWrapUsecase
/**
 * Connected [com.no1.taiwan.newsbasket.domain.BaseUsecase] and unwrapping and letting the usecase
 * become a await [Deferred] object without the mapper (Because the variables should be primitive variable).
 *
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Any, V : RequestValues> AsyncCaseWithResp<M, V>.toRun(
    parameter: V? = null
) = executeWrap(parameter)

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase] and unwrapping and letting the
 * usecase become a await [Deferred] object with the mapper.
 *
 * @param mapper the mapper for translating from [Model] to [Entity].
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues> AsyncCaseWithResp<M, V>.toRun(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = executeWrap(parameter).run { mapToEntity(mapper) }

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase] and unwrapping and letting the
 * usecase become a await [Deferred] object. with the mapper.
 *
 * @param mapper the mapper for translating from List<[Model]> to List<[Entity]>.
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues, MS : List<M>> AsyncCaseWithResp<MS, V>.toRunList(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = executeWrap(parameter).run { mapToEntities(mapper) }
//endregion

/**
 * A mapper which unboxing the [NewsResponse]<[Model]> then getting items we needs. Make a [NewsResponse]
 * again and boxing the [Entity] which mapping from [Model] to [Entity] to be a [NewsResponse]<[Entity]>.
 */
private infix fun <M : Model, E : Entity> NewsResponse<M>.mapToEntity(mapper: Mapper<M, E>) =
    data?.let(mapper::toEntityFrom)?.wrapInSuccess() ?: "No response result".wrapInError<E>()

/**
 * A mapper which unboxing the [NewsResponse]<List<[Model]>> then getting items we needs. Make a [NewsResponse]
 * again and boxing the List<[Entity]> which mapping from List<[Model]> to List<[Entity]> to be a
 * [NewsResponse]<List<[Entity]>>.
 */
private infix fun <M : Model, E : Entity, MS : List<M>> NewsResponse<MS>.mapToEntities(mapper: Mapper<M, E>) =
    data?.map(mapper::toEntityFrom)?.wrapListInSuccess() ?: "No response result".wrapInError<List<E>>()

/**
 * Wrapping the [this] into [Success].
 */
private inline fun <E> E.wrapInSuccess() = Success(this)

/**
 * Wrapping the List<[this]> into [Success].
 */
private inline fun <E, ES : List<E>> ES.wrapListInSuccess() = Success(this)

/**
 * Wrapping the [String] msg into [Error].
 */
private inline fun <E> String.wrapInError() = Error<E>(msg = this)

// ===============================================================

/**
 * Connected [com.no1.taiwan.newsbasket.domain.BaseUsecase] and unwrapping and letting the usecase
 * become a await [Deferred] object without the mapper (Because the variables should be primitive variable).
 *
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Any, V : RequestValues> AsyncCaseWithResp<M, V>.toRaw(
    parameter: V? = null
) = execute(parameter)

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase] and unwrapping and letting the
 * usecase become a await [Deferred] object with the mapper.
 *
 * @param mapper the mapper for translating from [Model] to [Entity].
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues> AsyncCaseWithResp<M, V>.toRaw(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = execute(parameter).run { mapToEntity(mapper) }

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase] and unwrapping and letting the
 * usecase become a await [Deferred] object. with the mapper.
 *
 * @param mapper the mapper for translating from List<[Model]> to List<[Entity]>.
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues, MS : List<M>> AsyncCaseWithResp<MS, V>.toRaws(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = execute(parameter).run { mapToEntities(mapper) }
//endregion

/**
 * A mapper which unboxing the [NewsResponse]<[Model]> then getting items we needs. Make a [NewsResponse]
 * again and boxing the [Entity] which mapping from [Model] to [Entity] to be a [NewsResponse]<[Entity]>.
 */
private infix fun <M : Model, E : Entity> M.mapToEntity(mapper: Mapper<M, E>) =
    let(mapper::toEntityFrom)

/**
 * A mapper which unboxing the [NewsResponse]<List<[Model]>> then getting items we needs. Make a [NewsResponse]
 * again and boxing the List<[Entity]> which mapping from List<[Model]> to List<[Entity]> to be a
 * [NewsResponse]<List<[Entity]>>.
 */
private infix fun <M : Model, E : Entity, MS : List<M>> MS.mapToEntities(mapper: Mapper<M, E>) =
    map(mapper::toEntityFrom)

// ==============================================================================

/**
 * Connected [com.no1.taiwan.newsbasket.domain.BaseUsecase] and unwrapping and letting the usecase
 * become a await [Deferred] object without the mapper (Because the variables should be primitive variable).
 *
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Any, V : RequestValues> AsyncCase<M, V>.toRaw(
    parameter: V? = null
) = execute(parameter)

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase] and unwrapping and letting the
 * usecase become a await [Deferred] object with the mapper.
 *
 * @param mapper the mapper for translating from [Model] to [Entity].
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues> AsyncCase<M, V>.toRaw(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = execute(parameter).run { mapToEntity(mapper) }

/**
 * Connected [com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase] and unwrapping and letting the
 * usecase become a await [Deferred] object. with the mapper.
 *
 * @param mapper the mapper for translating from List<[Model]> to List<[Entity]>.
 * @param parameter the usecase's parameter.
 */
suspend fun <M : Model, E : Entity, V : RequestValues, MS : List<M>> AsyncCase<MS, V>.toRaws(
    mapper: Mapper<M, E>,
    parameter: V? = null
) = execute(parameter).run { mapToEntities(mapper) }
//endregion
