package com.no1.taiwan.newsbasket.internal.di

import android.content.Context
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.no1.taiwan.newsbasket.data.datas.mappers.NewsMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.TokenMapper
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper
import com.no1.taiwan.newsbasket.entities.mappers.NewsEntityMapper
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.provider
import org.kodein.di.generic.setBinding
import org.kodein.di.generic.singleton

/**
 * To provide the necessary utility objects for the whole app.
 */
object UtilModule {
    fun utilProvider(context: Context) = Module("Util Module") {
        /** ViewModel Set for [com.no1.taiwan.newsbasket.widget.viewmodel.ViewModelFactory] */
        bind() from setBinding<ViewModelEntry>()
        /** Mapper Set for [com.no1.taiwan.newsbasket.data.datas.mappers.Mapper] */
        bind() from setBinding<DataMapperEntry>()

        // OPTIMIZE(jieyi): 2018/10/16 We might use Gson for mapping data.
        bind<Gson>() with singleton {
            with(GsonBuilder()) {
                setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
                setLenient()
                create()
            }
        }

        /** Data Layer Mapper */
        bind<DataMapperEntry>().inSet() with provider { NewsMapper::class.java to NewsMapper() }
        bind<DataMapperEntry>().inSet() with provider { TokenMapper::class.java to TokenMapper() }

        // TODO(jieyi): 2018/09/19 Doing as like the domain can find the mapper.
        /** Presentation Layer Mapper */
        bind<PresentationNewsMapper>() with singleton { NewsEntityMapper() }
    }
}
