package com.skilltory.app.aparattv.ui.base

import com.skilltory.app.aparattv.ui.widghet.LoadingVisibility

interface UICommunicationImpl:LoadingVisibility {
    fun uiCommunicate(uiCommunication: UICommunication)
}