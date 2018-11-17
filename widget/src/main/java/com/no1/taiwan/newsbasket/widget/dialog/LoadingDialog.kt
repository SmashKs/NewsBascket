package com.no1.taiwan.newsbasket.widget.dialog

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devrapid.dialogbuilder.support.DialogFragmentTemplate.Builder
import com.devrapid.dialogbuilder.support.QuickDialogFragment
import com.no1.taiwan.newsbasket.widget.R

object LoadingDialog {
    fun getInstance(fragment: Fragment) = QuickDialogFragment.Builder(fragment) {
        builder()
    }.build()

    fun getInstance(activity: AppCompatActivity) = QuickDialogFragment.Builder(activity) {
        builder()
    }.build()

    private fun Builder.builder() {
        viewResCustom = R.layout.dialog_loading
    }
}
