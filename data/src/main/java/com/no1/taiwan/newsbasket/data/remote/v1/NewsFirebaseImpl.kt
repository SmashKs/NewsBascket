package com.no1.taiwan.newsbasket.data.remote.v1

import com.google.firebase.database.FirebaseDatabase
import com.no1.taiwan.newsbasket.data.remote.services.NewsFirebase

class NewsFirebaseImpl constructor(
    private val database: FirebaseDatabase
) : NewsFirebase
