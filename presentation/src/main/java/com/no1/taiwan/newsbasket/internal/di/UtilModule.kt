package com.no1.taiwan.newsbasket.internal.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.no1.taiwan.newsbasket.data.datas.mappers.ArticleMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.NewsMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.SourceMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.TokenMapper
import com.no1.taiwan.newsbasket.entities.mappers.NewsArticleEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.NewsEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.NewsSourceEntityMapper
import com.no1.taiwan.newsbasket.entities.mappers.TokenEntityMapper
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel
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
        // OPTIMIZE(jieyi): 2018/10/16 We might use Gson for mapping data.
        bind<Gson>() with singleton {
            with(GsonBuilder()) {
                setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
                setLenient()
                create()
            }
        }

        // Linear Layout Manager.
        bind<LinearLayoutManager>(ObjectLabel.LINEAR_LAYOUT_VERTICAL) with provider {
            LinearLayoutManager(context, VERTICAL, false)
        }
        bind<LinearLayoutManager>(ObjectLabel.LINEAR_LAYOUT_HORIZONTAL) with provider {
            LinearLayoutManager(context, HORIZONTAL, false)
        }
    }

    /**
     * Import this module in the [com.no1.taiwan.newsbasket.App] because data layer needs this.
     */
    fun dataUtilProvider() = Module("Data Layer Util") {
        /** Mapper Set for [com.no1.taiwan.newsbasket.data.datas.mappers.Mapper] */
        bind() from setBinding<DataMapperEntry>()

        /** Data Layer Mapper */
        bind<DataMapperEntry>().inSet() with provider { NewsMapper::class.java to NewsMapper() }
        bind<DataMapperEntry>().inSet() with provider { TokenMapper::class.java to TokenMapper() }
        bind<DataMapperEntry>().inSet() with provider { ArticleMapper::class.java to ArticleMapper(SourceMapper()) }
        bind<DataMapperEntry>().inSet() with provider { SourceMapper::class.java to SourceMapper() }
    }

    /**
     * Import this module for each activity entry, they don't be needed in the beginning.
     */
    fun presentationUtilProvider(context: Context) = Module("Presentation Layer Util") {
        /** Mapper Set for [com.no1.taiwan.newsbasket.entities.mappers.Mapper] */
        bind() from setBinding<PresentationMapperEntry>()

        /** Presentation Layer Mapper */
        bind<PresentationMapperEntry>().inSet() with singleton { NewsEntityMapper::class.java to NewsEntityMapper() }
        bind<PresentationMapperEntry>().inSet() with singleton { TokenEntityMapper::class.java to TokenEntityMapper() }
        bind<PresentationMapperEntry>().inSet() with singleton {
            NewsArticleEntityMapper::class.java to NewsArticleEntityMapper(NewsSourceEntityMapper())
        }
        bind<PresentationMapperEntry>().inSet() with singleton { NewsSourceEntityMapper::class.java to NewsSourceEntityMapper() }
    }
}
