package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import org.kodein.di.Kodein.Module

/**
 * To provide the necessary objects for the specific fragments.
 */
object SuperFragmentModule {
    fun fragmentModule() = Module("All Fragments Module") {
        // Import all of the fragment modules.
    }
}
