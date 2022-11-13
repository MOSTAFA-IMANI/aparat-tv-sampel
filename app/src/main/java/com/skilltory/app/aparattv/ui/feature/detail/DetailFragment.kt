package com.skilltory.app.aparattv.ui.feature.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.leanback.app.DetailsSupportFragment
import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.ui.MainActivity
import com.skilltory.app.aparattv.ui.base.UICommunicationTasks

class DetailFragment:DetailsSupportFragment(),UICommunicationTasks {

    //todo : can improve and implement later

    private val navArgs: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        expandAppBar(requireActivity() as MainActivity)
        navArgs.video.title?.let{
            setAppBarTitleText(requireActivity() as MainActivity, it)
        }
    }
}