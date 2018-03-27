@file:Suppress("NOTHING_TO_INLINE")

package org.glavo.viewer.util

import java.util.*
import kotlin.experimental.and

class HexString(data: ByteArray) {
    companion object {
        const val BYTES_PER_ROW = 16

        val byteStrings: Array<String> = Array(256) {
            String.format("%02X", it)
        }

        inline fun byteToString(b: Byte): String = byteStrings[b.toInt() and 0xFF]
    }

    val rowHeaderText: String = StringBuilder((data.size / BYTES_PER_ROW + 1) * 9).apply {
        var i = 0
        while (i < data.size) {
            append(String.format("%08X\n", i)) // row number
            i += BYTES_PER_ROW
        }
    }.toString()

    val byteText: String = StringBuilder(data.size * 3).apply {
        data.asList().asSequence().chunked(BYTES_PER_ROW).forEach {
            it.joinTo(this, separator = " ", postfix = "\n", transform = ::byteToString)
        }
    }.toString()

    val asciiText: String = StringBuilder((BYTES_PER_ROW + 1) * (data.size / BYTES_PER_ROW + 1)).apply {
        data.asList().asSequence().chunked(BYTES_PER_ROW).forEach {
            it.joinTo(this, separator = "", postfix = "\n") {
                if (it.toChar() in '!'..'~') {
                    it.toChar().toString()
                } else {
                    "."
                }

            }
        }
    }.toString()
}