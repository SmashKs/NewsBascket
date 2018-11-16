package com.no1.taiwan.newsbasket.data.datas.mappers

import com.no1.taiwan.newsbasket.data.datas.Data
import com.no1.taiwan.newsbasket.domain.models.Model

/**
 * The mapper is used to transition the object between [Data] and [Model].
 */
interface Mapper<D : Data, M : Model> {
    /**
     * Transition the [Data] object to [Model] object.
     *
     * @param data a [Data] data object.
     * @return the same content's [Model] object.
     */
    fun toModelFrom(data: D): M

    /**
     * Transition the [Data] object to [Model] object.
     *
     * @param model a [Model] data object.
     * @return the same content's [Data] object.
     */
    fun toDataFrom(model: M): D
}
