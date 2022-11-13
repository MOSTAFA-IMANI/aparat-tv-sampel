package com.skilltory.app.aparattv.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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

    lateinit var navController: NavController

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->

            val currentFragment = navHostFragment.childFragmentManager.fragments[0]
            Log.w("dsfsdf", currentFragment.toString())

            setAppBarVisibility(destination.id)

        }

    }

    private fun setAppBarVisibility(fragmentId: Int) {
        when (fragmentId) {
            R.id.browsFragment,
            R.id.detailFragment,
            -> binding.appBar.visibility = View.VISIBLE
            else -> binding.appBar.visibility = View.GONE
        }
    }

    override fun uiCommunicate(uiCommunication: UICommunication) {
        when (uiCommunication) {
            is UICommunication.ShowSnackbar -> {

                snackbarUtil.showSnackbar(
                    view = uiCommunication.view ?: binding.snack,
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

    override fun expandAppBar() {
        Log.i(Constants.TAG_APPBAR, "expandAppBar: Main")
        binding.appBar.setExpanded(true, true)
    }

    override fun collapseAppBar() {
        Log.i(Constants.TAG_APPBAR, "collapseAppBar: Main")
        binding.appBar.setExpanded(false, true)
    }

    override fun setAppBarTitleText(text: String) {
        binding.appBarTitle.text = text
    }
}