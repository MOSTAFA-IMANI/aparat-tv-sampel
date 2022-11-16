package com.skilltory.app.aparattv.utils.extension

import org.junit.Assert.assertEquals
import org.junit.Test

internal class StringExtensionKtTest {

    /**
     * toUniqueId()
     * is a fun for converting string id to Int Id
     * */
    @Test
    fun `toUniqueId must return valid result`() {
        val inputLowerCase = "12a28"
        val inputUpperCase = "12B28"
        assertEquals(12128L,inputLowerCase.toUniqueId())
        assertEquals(12228L,inputUpperCase.toUniqueId())
    }
    @Test
    fun `toUniqueId must return null for not valid input`() {
        val inputNotValid1 = "12'28"
        assertEquals(null,inputNotValid1.toUniqueId())
    }
}