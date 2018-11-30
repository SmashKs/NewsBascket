package com.no1.taiwan.newsbasket.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.devrapid.dialogbuilder.support.QuickDialogFragment
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.ext.showLoadingView
import com.no1.taiwan.newsbasket.widget.dialog.LoadingDialog
import org.kodein.di.generic.instance
import java.lang.reflect.ParameterizedType

/**
 * The basic activity is in MVVM architecture which prepares all necessary variables or functions.
 */
abstract class AdvActivity<out VM : ViewModel> : BaseActivity(), LoadView {
    /** Add the AAC [ViewModel] for each fragments. */
    @Suppress("UNCHECKED_CAST")
    protected val vm
        get() = vmCreateMethod.invoke(vmProviders, vmConcreteClass) as? VM ?: throw ClassCastException()

    private val viewModelFactory by instance<ViewModelProvider.Factory>()
    /** [VM] is the first (index: 0) in the generic declare. */
    private val vmConcreteClass
        get() = cast<Class<*>>(cast<ParameterizedType>(this::class.java.genericSuperclass).actualTypeArguments[0])
    private val vmProviders get() = ViewModelProviders.of(this, viewModelFactory)
    /** The [ViewModelProviders.of] function for obtaining a [ViewModel]. */
    private val vmCreateMethod get() = vmProviders.javaClass.getMethod("get", vmConcreteClass.superclass.javaClass)
    /** Dialog loading view. */
    private val loadingView by lazy { LoadingDialog.getInstance(this) }
    /** Enable dialog loading view or use loading layout. */
    protected open var enableDialogLoading = true

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    override fun showLoading() = if (enableDialogLoading) loadingView.show() else showLoadingView()

    /**
     * Hide a loading view.
     */
    override fun hideLoading() = if (enableDialogLoading)
        loadingView.takeUnless(QuickDialogFragment::isDismiss)?.dismiss() ?: Unit
    else
        showLoadingView()

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    override fun showRetry() = Unit

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    override fun hideRetry() = Unit

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    override fun showError(message: String) = Unit
}
