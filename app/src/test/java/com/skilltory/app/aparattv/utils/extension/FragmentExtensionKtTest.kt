package com.skilltory.app.aparattv.utils.extension

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
internal class FragmentExtensionKtTest {

    @Mock
    lateinit var navDirections:NavDirections

    @Spy
    lateinit var fragment: Fragment

    @Mock
    lateinit var navController: NavController

    @Test
    fun `canNavigate is false`() {
        fragment= spy()
        navDirections= mock()
        navController = mock()

        doReturn(false).whenever(fragment).canNavigate()

        fragment.navigate(navDirections)
        verify(fragment, atLeastOnce()).canNavigate()
        verify(fragment, never()).navigateSafe(navDirections)
    }


}