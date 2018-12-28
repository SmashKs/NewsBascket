package com.no1.taiwan.newsbasket.bases

import android.os.Bundle
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.devrapid.dialogbuilder.support.QuickDialogFragment
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT
import com.no1.taiwan.newsbasket.ext.const.isDefault
import com.no1.taiwan.newsbasket.ext.hideLoadingView
import com.no1.taiwan.newsbasket.ext.hideRetryView
import com.no1.taiwan.newsbasket.ext.showErrorView
import com.no1.taiwan.newsbasket.ext.showLoadingView
import com.no1.taiwan.newsbasket.ext.showRetryView
import com.no1.taiwan.newsbasket.widget.dialog.LoadingDialog
import org.kodein.di.generic.instance
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * The basic fragment is in MVVM architecture which prepares all necessary variables or functions.
 */
abstract class AdvFragment<out A : BaseActivity, out VM : ViewModel> : BaseFragment<A>(), LoadView {
    protected open val genericVMIndex = DEFAULT_INT
    protected val vmProviders get() = ViewModelProviders.of(this, viewModelFactory)
    /** Add the AAC [ViewModel] for each fragments. */
    @Suppress("UNCHECKED_CAST")
    protected val vm
        get() = vmCreateMethod.invoke(vmProviders, vmConcreteClass) as? VM ?: throw ClassCastException()
    private val viewModelFactory by instance<ViewModelProvider.Factory>()
    /** [VM] is the first (index: 1) in the generic declare. */
    private val vmConcreteClass: Class<*>
        get() {
            // Get the all generic data types.
            val actualTypeArguments =
                cast<ParameterizedType>(recursiveFindGenericSuperClass(this::class.java)).actualTypeArguments

            // If we don't set viewmodel index by ourselves, it can find the first generic viewmodel.
            val viewmodelClass = if (genericVMIndex.isDefault())
            // Recursively find the first generic viewmodel data type.
                actualTypeArguments.firstOrNull { checkAllSuperClass(cast(it), ViewModel::class.java) }
            else
            // Customize index.
                actualTypeArguments[genericVMIndex]

            return cast(viewmodelClass)
        }
    /** The [ViewModelProviders.of] function for obtaining a [ViewModel]. */
    private val vmCreateMethod get() = vmProviders.javaClass.getMethod("get", vmConcreteClass.superclass.javaClass)
    /** Dialog loading view. */
    private val loadingView by lazy { LoadingDialog.getInstance(this, true) }
    /** Enable dialog loading view or use loading layout. */
    protected open var enableDialogLoading = true

    //region Fragment's lifecycle.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindLiveData()
    }
    //endregion

    //region View Implementation for the Presenter.
    @UiThread
    override fun showLoading() = if (enableDialogLoading) loadingView.show() else parent.showLoadingView()

    @UiThread
    override fun hideLoading() = if (enableDialogLoading)
        loadingView.takeUnless(QuickDialogFragment::isDismiss)?.dismiss() ?: Unit
    else
        parent.hideLoadingView()

    @UiThread
    override fun showRetry() = parent.showRetryView()

    @UiThread
    override fun hideRetry() = parent.hideRetryView()

    @UiThread
    override fun showError(message: String) = parent.showErrorView(message)
    //endregion

    /** The block of binding to [androidx.lifecycle.ViewModel]'s [androidx.lifecycle.LiveData]. */
    @UiThread
    protected open fun bindLiveData() = Unit

    private fun recursiveFindGenericSuperClass(superclass: Class<*>): Type =
        if (superclass.genericSuperclass is ParameterizedType)
            requireNotNull(superclass.genericSuperclass)
        else
            recursiveFindGenericSuperClass(requireNotNull(superclass.superclass))

    private fun checkAllSuperClass(objClass: Class<*>, assignable: Class<*>): Boolean {
        objClass.superclass?.takeUnless { it.isAssignableFrom(java.lang.Object::class.java) }?.let {
            return if (it.isAssignableFrom(assignable))
                true
            else
                checkAllSuperClass(it, assignable)
        } ?: return false
    }
}
