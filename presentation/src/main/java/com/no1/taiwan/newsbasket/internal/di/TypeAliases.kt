package com.no1.taiwan.newsbasket.internal.di

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.no1.taiwan.newsbasket.data.datas.DataMapper

typealias ViewModelEntry = Pair<Class<out ViewModel>, ViewModel>
typealias ViewModelEntries = Set<ViewModelEntry>

typealias ViewHolderEntry = Pair<Int, Pair<Int, (View) -> RecyclerView.ViewHolder>>
typealias ViewHolderEntries = Set<ViewHolderEntry>

typealias DataMapperEntry = Pair<Class<out DataMapper>, DataMapper>
typealias DataMapperEntries = Set<DataMapperEntry>
