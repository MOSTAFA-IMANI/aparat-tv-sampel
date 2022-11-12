package com.skilltory.app.aparattv.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes


sealed class UICommunication {

    data class ShowSnackbar constructor(
        val message: String,
        val isSticky: Boolean = false,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null,
        var view: View? = null,
    ) : UICommunication()

    // can add other UI Communication method class
    // for example Toast or Empty State ,...
}