package com.no1.taiwan.newsbasket.data.local.services

/**
 * Using prefix name (retrieve), (insert), (replace), (release)
 */
interface AdBlockerService {
    fun retrieveBlockList(): List<String>
}
