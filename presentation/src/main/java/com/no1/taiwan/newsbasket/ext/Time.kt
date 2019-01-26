package com.no1.taiwan.newsbasket.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.timeTranslate(pattern: String = "yyyy-MM-dd'T'HH:mm:ss'Z'", locale: Locale = Locale.JAPAN) =
    Calendar.getInstance().let { SimpleDateFormat(pattern, locale).parse(this) }

fun Date.output(pattern: String = "yyyy-MM-dd HH:mm", locale: Locale = Locale.JAPAN) =
    SimpleDateFormat(pattern, locale).format(this)
