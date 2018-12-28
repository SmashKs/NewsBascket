package com.no1.taiwan.newsbasket.bases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.annotation.UiThread
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.devrapid.kotlinknifer.hideSoftKeyboard
import com.devrapid.kotlinshaver.castOrNull
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.internal.di.dependency.fragment.SuperFragmentModule.fragmentModule
import org.jetbrains.anko.findOptional
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.findOptional
import org.kodein.di.Kodein.Companion.lazy
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

/**
 * The basic fragment is for the normal activity which prepares all necessary variables or functions.
 */
abstract class BaseFragment<out A : BaseActivity> : Fragment(), KodeinAware {
    override val kodein = lazy {
        extend(parentKodein)
        /* fragment specific bindings */
        import(fragmentModule())
    }
    @Suppress("UNCHECKED_CAST")
    protected val parent
        get() = requireActivity() as A  // If there's no parent, forcing crashing the app.
    protected val appContext by instance<Context>()
    private var rootView: View? = null
    private val parentKodein by closestKodein()

    //region Fragment lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Keep the instance data.
        retainInstance = true

        val localInflater = customTheme()?.let {
            // Create ContextThemeWrapper from the original Activity Context with the custom theme
            val contextThemeWrapper = ContextThemeWrapper(activity, it)
            // Clone the inflater using the ContextThemeWrapper
            inflater.cloneInContext(contextThemeWrapper)
        } ?: inflater

        // FIXED: https://www.zybuluo.com/kimo/note/255244
        // inflate the layout using the cloned inflater, not default inflater
        rootView ?: let { rootView = localInflater.inflate(provideInflateView(), null) }
        val parent = castOrNull<ViewGroup>(rootView?.parent)
        parent?.removeView(rootView)

        return rootView
    }

    /**
     * For initializing the view components and setting the listeners.
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the title into the support action bar.
        parent.setSupportActionBar(findOptional(R.id.tb_header))
        actionBarTitle()?.let { parent.supportActionBar?.title = it }
        // Before setting.
        viewComponentBinding()
        componentListenersBinding()
        // Action for customizing.
        rendered(savedInstanceState)
        // When the fragment has base_layout id, it'll attach the function of hiding soft keyboard.
        view.findOptional<View>(R.id.base_layout)?.clickedThenHideKeyboard()
    }
    //endregion

    /**
     * Initialize doing some methods or actions here.
     *
     * @param savedInstanceState previous status.
     */
    @UiThread
    protected abstract fun rendered(savedInstanceState: Bundle?)

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    @UiThread
    @LayoutRes
    protected abstract fun provideInflateView(): Int

    /**
     * For separating the huge function code in [rendered]. Initialize all view components here.
     */
    @UiThread
    protected open fun viewComponentBinding() = Unit

    /**
     * For separating the huge function code in [rendered]. Initialize all component listeners here.
     */
    @UiThread
    protected open fun componentListenersBinding() = Unit

    /**
     * Set specific theme to this fragment.
     *
     * @return [Int] style xml resource.
     */
    @UiThread
    @StyleRes
    protected open fun customTheme(): Int? = null

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    @UiThread
    protected open fun actionBarTitle(): String? = null

    /**
     * Attaching the function of hiding the soft keyboard into a [View].
     */
    @UiThread
    protected fun View.clickedThenHideKeyboard() {
        if (!hasOnClickListeners()) onClick { hideSoftKeyboard() }
    }
}
