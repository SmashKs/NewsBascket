package com.no1.taiwan.newsbasket.services

import android.app.IntentService
import android.content.Intent
import com.devrapid.kotlinknifer.SharedPrefs
import com.tencent.mmkv.MMKV
import org.jetbrains.anko.defaultSharedPreferences

class InitialService : IntentService(SERVICE_NAME) {
    companion object {
        private const val SERVICE_NAME = "Initialize Intent Service"
    }

    override fun onHandleIntent(intent: Intent) {
        init()
    }

    private fun init() {
        // key-value storage, choose one for using.
        SharedPrefs.setPrefSettings(defaultSharedPreferences)
        MMKV.initialize(this)

//        Stetho.initialize(Stetho.newInitializerBuilder(this)
//                              .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                              .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                              .build())
    }
}
