package com.skilltory.app.aparattv.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.databinding.ActivityMainBinding
import com.skilltory.app.aparattv.ui.base.UICommunication
import com.skilltory.app.aparattv.ui.base.UICommunicationImpl
import com.skilltory.app.aparattv.utils.Constants
import com.skilltory.app.aparattv.utils.SnackbarUtil
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : FragmentActivity(), UICommunicationImpl {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var snackbarUtil: SnackbarUtil

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun uiCommunicate(uiCommunication: UICommunication) {
        when (uiCommunication) {
            is UICommunication.ShowSnackbar -> {

              snackbarUtil.showSnackbar(
                    view = uiCommunication.view?:binding.snack,
                    message = uiCommunication.message.toIntOrNull()?.let { getString(it) }
                        ?: uiCommunication.message,
                    isSticky = uiCommunication.isSticky,
                    actionTitle = uiCommunication.actionTitle?.toIntOrNull()?.let { getString(it) }
                        ?: uiCommunication.actionTitle,
                    action = uiCommunication.action
                )

            }
        }
    }


    override fun showLoading() {
        Log.i(Constants.TAG_LOADING, "showLoading: Main ")
        binding.loadingProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        Log.i(Constants.TAG_LOADING, "hideLoading: Main")
        binding.loadingProgressBar.visibility = View.GONE
    }
}