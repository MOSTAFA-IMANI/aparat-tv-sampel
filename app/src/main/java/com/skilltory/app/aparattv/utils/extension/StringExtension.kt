package com.skilltory.app.aparattv.utils.extension


/**
 * toUniqueId()
 * is a fun for converting string id to Int Id.
 * Algorithm: converting character in String which is not number to unique number
 * */
fun String.toUniqueId(): Long {
    var result = ""
    val str = this
    for (i in str.indices) {
        val ch: Char = str[i]
        result = if (Character.isLetter(ch)) {
            val initialCharacter = if (Character.isUpperCase(ch)) 'A' else 'a'
            result + (ch - initialCharacter + 1).toString()
        } else result + ch
    }

    return result.toLong()
}