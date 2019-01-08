package com.no1.taiwan.newsbasket.ext.handler

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.devrapid.kotlinknifer.SoftRef
import com.hwangjr.rxbus.RxBus

class BusFragLifeRegister(fragment: Fragment) : LifecycleObserver {
    private val frag by SoftRef(fragment)

    init {
        frag?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(ON_RESUME)
    fun registerRxBus() {
        frag?.run(RxBus.get()::register)
    }

    @OnLifecycleEvent(ON_PAUSE)
    fun unregisterRxBus() {
        frag?.run(RxBus.get()::unregister)
    }
}

class BusActLifeRegister(activity: AppCompatActivity) : LifecycleObserver {
    private val act by SoftRef(activity)

    init {
        act?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(ON_RESUME)
    fun registerRxBus() {
        act?.run(RxBus.get()::register)
    }

    @OnLifecycleEvent(ON_PAUSE)
    fun unregisterRxBus() {
        act?.run(RxBus.get()::unregister)
    }
}
