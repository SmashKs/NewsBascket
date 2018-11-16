@file:Suppress("NOTHING_TO_INLINE")

package com.no1.taiwan.newsbasket.ext.const

const val DEFAULT_INT = -1
const val DEFAULT_LONG = -1L
const val DEFAULT_DOUBLE = -1.0
const val DEFAULT_FLOAT = -1f
const val DEFAULT_STR = ""

inline fun Int.isDefault() = DEFAULT_INT == this
inline fun Long.isDefault() = DEFAULT_LONG == this
inline fun String.isDefault() = DEFAULT_STR == this
inline fun Double.isDefault() = DEFAULT_DOUBLE == this
inline fun Float.isDefault() = DEFAULT_FLOAT == this

inline fun <T> Int.takeIfDefault(block: (Int) -> T) = takeIf(Int::isDefault)?.let(block)
inline fun <T> Long.takeIfDefault(block: (Long) -> T) = takeIf(Long::isDefault)?.let(block)
inline fun <T> String.takeIfDefault(block: (String) -> T) = takeIf(String::isDefault)?.let(block)
inline fun <T> Double.takeIfDefault(block: (Double) -> T) = takeIf(Double::isDefault)?.let(block)
inline fun <T> Float.takeIfDefault(block: (Float) -> T) = takeIf(Float::isDefault)?.let(block)

inline fun <T> Int.takeUnlessDefault(block: (Int) -> T) = takeUnless(Int::isDefault)?.let(block)
inline fun <T> Long.takeUnlessDefault(block: (Long) -> T) = takeUnless(Long::isDefault)?.let(block)
inline fun <T> String.takeUnlessDefault(block: (String) -> T) = takeUnless(String::isDefault)?.let(block)
inline fun <T> Double.takeUnlessDefault(block: (Double) -> T) = takeUnless(Double::isDefault)?.let(block)
inline fun <T> Float.takeUnlessDefault(block: (Float) -> T) = takeUnless(Float::isDefault)?.let(block)
