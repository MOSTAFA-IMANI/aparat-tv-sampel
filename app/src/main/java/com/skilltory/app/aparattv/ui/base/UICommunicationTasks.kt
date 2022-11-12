package com.skilltory.app.aparattv.ui.base

import android.app.Activity
import com.skilltory.app.aparattv.ui.MainActivity
import com.skilltory.app.aparattv.utils.Resource

/**
 * here we can list all functions that must communicate with main Ui
 * it's better to handle by BaseFragment
 * but as we use lean back SupportFragment for each
 * as we have purpose Single Activity I decided to implement faster way :)
* */
interface UICommunicationTasks {
    fun handleLoading(activity: MainActivity,isLoading:Boolean){
        if(isLoading){
            activity.showLoading()
        }
        else{
            activity.hideLoading()
        }
    }
    fun handleError(activity: MainActivity,error: Resource.Error<*>){
       error.uiCommunication?.let {
           activity.uiCommunicate(it)
       }?: run{
           val errorMessage = error.message
           //...
           //todo: can check error Message and do Some Exceptions when There is an Error
       }
    }
}