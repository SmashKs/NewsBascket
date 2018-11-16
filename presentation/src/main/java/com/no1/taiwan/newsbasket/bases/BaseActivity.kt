package com.no1.taiwan.newsbasket.bases

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hwangjr.rxbus.Bus
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntries
import com.no1.taiwan.newsbasket.internal.di.dependency.activity.SuperActivityModule.activityModule
import com.no1.taiwan.newsbasket.widget.viewmodel.ViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext
import org.kodein.di.generic.singleton

/**
 * The basic activity is for the normal activity which prepares all necessary variables or functions.
 */
abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    override val kodeinContext get() = kcontext(this)
    override val kodein by retainedKodein {
        extend(parentKodein)
        /* activity specific bindings */
        import(activityModule())

        bind<ViewModelProvider.Factory>() with singleton {
            ViewModelFactory(instance(), instance<ViewModelEntries>().toMap().toMutableMap())
        }
    }
    protected val bus by instance<Bus>()
    private val parentKodein by closestKodein()

    //region RxBus Example
    // OPTIMIZE(jieyi): 2018/04/19 This's a simple example for RxBus.
    // Register it in the parent class that it will be not reflected.
    protected var busEvent = object {
//        @Subscribe(tags = [Tag(RxbusTag.NAVIGATOR)])
//        fun test(test: String) { }
    }
    //endregion

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preSetContentView()
        setContentView(provideLayoutId())
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

    @UiThread
    protected abstract fun init(savedInstanceState: Bundle?)

    @UiThread
    protected open fun uninit() = Unit

    @UiThread
    protected fun preSetContentView() = Unit

    @LayoutRes
    abstract fun provideLayoutId(): Int
}
