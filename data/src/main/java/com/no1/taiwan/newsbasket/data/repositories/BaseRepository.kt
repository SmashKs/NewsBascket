package com.no1.taiwan.newsbasket.data.repositories

import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.data.datas.DataMapper
import com.no1.taiwan.newsbasket.data.datas.DataMapperPool

abstract class BaseRepository(protected val mapperPool: DataMapperPool) {
    /**
     * Get a mapper object from the mapper pool.
     */
    protected inline fun <reified DM : DataMapper> digDataMapper() = cast<DM>(mapperPool[DM::class.java])
}
