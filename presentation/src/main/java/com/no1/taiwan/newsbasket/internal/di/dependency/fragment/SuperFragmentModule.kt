package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.internal.di.dependency.fragment.IndexModule.indexProvider
import com.no1.taiwan.newsbasket.internal.di.dependency.fragment.KeywordModule.keywordProvider
import org.kodein.di.Kodein.Module

/**
 * To provide the necessary objects for the specific fragments.
 */
object SuperFragmentModule {
    fun fragmentModule() = Module("All Fragments Module") {
        // Import all of the fragment modules.
        import(indexProvider())
        import(keywordProvider())
    }
}
