package com.skilltory.app.aparattv.ui.base

import com.skilltory.app.aparattv.ui.widghet.AppBarExpander
import com.skilltory.app.aparattv.ui.widghet.LoadingVisibility

interface UICommunicationImpl:LoadingVisibility, AppBarExpander {
    fun uiCommunicate(uiCommunication: UICommunication)
}