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
        val input = "12a28"
        assertEquals("12128",input.toUniqueId())
    }
}