package com.no1.taiwan.newsbasket.ext.const

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Time {
    fun stringtoUnixTime(stringTime: String, formatter: String = "yyyy-MM-dd HH:mm:ss") =
        Calendar.getInstance().apply {
            time = SimpleDateFormat(formatter, Locale.getDefault()).parse(stringTime)
        }.timeInMillis
}
