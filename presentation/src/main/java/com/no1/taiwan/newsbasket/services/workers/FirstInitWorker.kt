package com.no1.taiwan.newsbasket.services.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.devrapid.kotlinknifer.SharedPrefs
import com.facebook.stetho.Stetho
import org.jetbrains.anko.defaultSharedPreferences

class FirstInitWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        // key-value storage, choose one for using.
        SharedPrefs.setPrefSettings(applicationContext.defaultSharedPreferences)
        Stetho.initializeWithDefaults(applicationContext)

        return Result.success()
    }
}
