package com.no1.taiwan.newsbasket.bases

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hwangjr.rxbus.Bus
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeFactory
import com.no1.taiwan.newsbasket.internal.di.AppModule.appProvider
import com.no1.taiwan.newsbasket.internal.di.RecyclerViewModule.recyclerViewProvider
import com.no1.taiwan.newsbasket.internal.di.UtilModule.presentationUtilProvider
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntries
import com.no1.taiwan.newsbasket.internal.di.dependency.activity.SuperActivityModule.activityModule
import com.no1.taiwan.newsbasket.widget.viewmodel.ViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * The basic activity is for the normal activity which prepares all necessary variables or functions.
 */
abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    //    override val kodeinContext: KodeinContext<AppCompatActivity> = kcontext(this)
    override val kodein by retainedKodein {
        extend(parentKodein)
        import(appProvider())
        /** activities or fragments */
        import(presentationUtilProvider(this@BaseActivity))
        import(recyclerViewProvider(this@BaseActivity))
        /* activity specific bindings */
        import(activityModule())

        bind<ViewModelProvider.Factory>() with provider {
            ViewModelFactory(instance(), instance<ViewModelEntries>().toMap().toMutableMap())
        }
    }
    // OPTIMIZE(jieyi): 2018/11/21 This might reduce some redundant injected objects.
    private val parentKodein by closestKodein()
    protected val bus by instance<Bus>()

    //region RxBus Example
    // OPTIMIZE(jieyi): 2018/04/19 This's a simple example for RxBus.
    // Register it in the parent class that it will be not reflected.
    protected var busEvent = object {
//        @Subscribe(tags = [Tag(RxbusTag.NAVIGATOR)])
//        fun test(test: String) { }
    }
    //endregion

    private val adapter by instance<MultiTypeFactory>()

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preSetContentView()
        setContentView(provideLayoutId())
        // Pre-set the view components.
        viewComponentBinding()
        componentListenersBinding()
        init(savedInstanceState)

        // Register RxBus.
        bus.register(busEvent)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister RxBus.
        bus.unregister(busEvent)
        uninit()
    }
    //endregion

    /**
     * Initialize doing some methods and actions.
     *
     * @param savedInstanceState previous state after this activity was destroyed.
     */
    @UiThread
    protected abstract fun init(savedInstanceState: Bundle?)

    /**
     * For separating the huge function code in [init]. Initialize all view components here.
     */
    @UiThread
    protected open fun viewComponentBinding() = Unit

    /**
     * For separating the huge function code in [init]. Initialize all component listeners here.
     */
    @UiThread
    protected open fun componentListenersBinding() = Unit

    @UiThread
    protected open fun uninit() = Unit

    @UiThread
    protected fun preSetContentView() = Unit

    @LayoutRes
    abstract fun provideLayoutId(): Int
}
