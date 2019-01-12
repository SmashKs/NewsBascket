package com.no1.taiwan.newsbasket.internal.di

import android.content.Context
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.no1.taiwan.newsbasket.data.datas.mappers.ArticleMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.NewsMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.SourceMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.TokenMapper
import com.no1.taiwan.newsbasket.entities.PresentationArticleMapper
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper
import com.no1.taiwan.newsbasket.entities.PresentationSourceMapper
import com.no1.taiwan.newsbasket.entities.PresentationTokenMapper
import com.no1.taiwan.newsbasket.entities.mappers.NewsArticleEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.NewsEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.NewsSourceEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.TokenEntityMapper
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
    fun utilProvider(context: Context) = Module("Util") {
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
        // TODO(jieyi): 2019-01-12 SourceMapper should be injected.
        bind<DataMapperEntry>().inSet() with provider { ArticleMapper::class.java to ArticleMapper(SourceMapper()) }
        bind<DataMapperEntry>().inSet() with provider { SourceMapper::class.java to SourceMapper() }
    }

    fun presentationUtilProvider(context: Context) = Module("Presentation Util") {
        /** ViewModel Set for [com.no1.taiwan.newsbasket.widget.viewmodel.ViewModelFactory] */
        bind() from setBinding<ViewModelEntry>()

        // TODO(jieyi): 2018/09/19 Doing as like the domain can find the mapper.
        /** Presentation Layer Mapper */
        bind<PresentationNewsMapper>() with singleton { NewsEntityMapper() }
        bind<PresentationTokenMapper>() with singleton { TokenEntityMapper() }
        bind<PresentationArticleMapper>() with singleton { NewsArticleEntityMapper(NewsSourceEntityMapper()) }
        bind<PresentationSourceMapper>() with singleton { NewsSourceEntityMapper() }
    }
}
