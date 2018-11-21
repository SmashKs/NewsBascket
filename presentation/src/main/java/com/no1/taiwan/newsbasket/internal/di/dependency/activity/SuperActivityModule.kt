package com.no1.taiwan.newsbasket.internal.di.dependency.activity

import com.no1.taiwan.newsbasket.internal.di.dependency.activity.MainModule.mainProvider
import org.kodein.di.Kodein.Module

/**
 * To provide the necessary objects for the specific activities.
 */
object SuperActivityModule {
    fun activityModule() = Module("All Activities Module") {
        // Import all of the activity modules.
        import(mainProvider())
    }
}
