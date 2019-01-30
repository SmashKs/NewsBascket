package com.no1.taiwan.newsbasket.data.local.v1

import android.content.Context
import com.no1.taiwan.newsbasket.data.local.services.AdBlockerService

class AdBlockImpl(context: Context) : AdBlockerService {
    private val appContext = context.applicationContext
    override fun retrieveBlockList(): List<String> {
        val fileInputStream = appContext.assets.open("adblock.txt") ?: throw RuntimeException("Cannot open file")
        lateinit var blockList: Set<String>

        fileInputStream.bufferedReader().useLines {
            blockList = it.toSet()
        }

        return blockList.toList()
    }
}
