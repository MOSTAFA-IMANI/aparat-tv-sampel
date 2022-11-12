package com.skilltory.app.aparattv.utils

import android.util.LayoutDirection
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

import com.skilltory.app.aparattv.ui.widghet.StickySnackbar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackbarUtil @Inject constructor() {
    private var stickySnackbar = StickySnackbar()

    fun showSnackbar(
        view: View,
        message: String,
        isSticky: Boolean,
        actionTitle: String? = null,
        action: (() -> Unit)? = null,
    ) {

        val behavior = BaseTransientBottomBar.Behavior().apply {
            setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
        }

        stickySnackbar.apply {
            this.isSticky = isSticky

            snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
                setBehavior(behavior)
                actionTitle?.let {
                    duration = BaseTransientBottomBar.LENGTH_INDEFINITE
                    setActionTextColor(
                        ContextCompat.getColor(
                            view.context,
                            androidx.appcompat.R.color.material_blue_grey_950
                        )
                    )
                    if (action == null)
                        setAction(actionTitle) { this.dismiss() }
                    else
                        setAction(actionTitle) { action() }
                }
            }
            snackbar?.show()
        }
    }



    fun disMissIndefiniteSnackbar() {
        stickySnackbar.let {
            it.snackbar?.let { snackbar ->
                if (snackbar.duration == BaseTransientBottomBar.LENGTH_INDEFINITE && !it.isSticky) {
                    snackbar.dismiss()
                }
            }
        }
    }
}