package com.no1.taiwan.newsbasket.internal.di.dependency.activity

import com.no1.taiwan.newsbasket.entities.PresentationMapperPool
import com.no1.taiwan.newsbasket.features.main.viewmodels.MainViewModel
import com.no1.taiwan.newsbasket.features.test.TestViewModel
import com.no1.taiwan.newsbasket.internal.di.PresentationMapperEntries
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import com.no1.taiwan.newsbasket.internal.di.dependency.activity.MainModule.mainProvider
import com.no1.taiwan.newsbasket.internal.di.dependency.activity.TestModule.testProvider
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.setBinding
import org.kodein.di.generic.singleton

/**
 * To provide the necessary objects for the specific activities.
 */
object SuperActivityModule {
    fun activityModule() = Module("All Activities") {
        // Import all of the activity modules.
        import(testProvider())
        import(mainProvider())

        /** ViewModel Set for [com.no1.taiwan.newsbasket.widget.viewmodel.ViewModelFactory] */
        bind() from setBinding<ViewModelEntry>()
        /** Mapper Pool for providing all data mappers */
        bind<PresentationMapperPool>() with singleton { instance<PresentationMapperEntries>().toMap() }

        import(providerViewModel())
    }

    private fun providerViewModel() = Module("Activity ViewModel") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with singleton { MainViewModel::class.java to MainViewModel() }
        bind<ViewModelEntry>().inSet() with singleton {
            TestViewModel::class.java to TestViewModel(instance(), instance())
        }
    }
}
