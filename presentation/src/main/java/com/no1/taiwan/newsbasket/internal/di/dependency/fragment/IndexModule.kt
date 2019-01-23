package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.entities.mappers.NewsSourceEntityMapper
import com.no1.taiwan.newsbasket.internal.di.PresentationMapperEntry
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.singleton

object IndexModule {
    fun indexProvider() = Module("Index Fragment") {
        bind<PresentationMapperEntry>().inSet() with singleton { NewsSourceEntityMapper::class.java to NewsSourceEntityMapper() }
    }
}
