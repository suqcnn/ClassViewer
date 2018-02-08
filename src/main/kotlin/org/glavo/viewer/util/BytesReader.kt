package org.glavo.viewer.util

import java.io.UTFDataFormatException
import java.nio.ByteBuffer
import java.nio.ByteOrder

open class BytesReader(val buf: ByteBuffer) {

    @JvmOverloads
    constructor(arr: ByteArray, order: ByteOrder = ByteOrder.BIG_ENDIAN)
            : this(ByteBuffer.wrap(arr).asReadOnlyBuffer().order(order))

    val position: Int
        get() = buf.position()

    fun getByte(index: Int): Byte {
        return buf.get(index)
    }

    fun getShort(index: Int): Short {
        return buf.getShort(index)
    }

    // 8-bit signed int
    fun readByte(): Byte {
        return buf.get()
    }

    // 8-bit unsigned int
    fun readUnsignedByte(): Int {
        return java.lang.Byte.toUnsignedInt(buf.get())
    }

    // 16-bit signed int
    fun readShort(): Short {
        return buf.short
    }

    // 16-bit unsigned int
    fun readUnsignedShort(): Int {
        return java.lang.Short.toUnsignedInt(buf.short)
    }

    // 32-bit signed int
    fun readInt(): Int {
        return buf.int
    }

    // 32-bit unsigned int
    fun readUnsignedInt(): Long {
        return Integer.toUnsignedLong(buf.int)
    }

    // 64-bit signed int
    fun readLong(): Long {
        return buf.long
    }

    fun readFloat(): Float {
        return buf.float
    }

    fun readDouble(): Double {
        return buf.double
    }

    fun readUTF(): String {
        return readUTF(readUnsignedShort())
    }

    fun readUTF(len: Int): String {
        val bytes = readBytes(len)
        val chars = CharArray(len)

        var c: Int
        var char2: Int
        var char3: Int
        var count = 0
        var chararr_count = 0

        while (count < len) {
            c = bytes[count].toInt() and 0xff
            if (c > 127) break
            count++
            chars[chararr_count++] = c.toChar()
        }

        while (count < len) {
            c = bytes[count].toInt() and 0xff
            when (c shr 4) {
                0, 1, 2, 3, 4, 5, 6, 7 -> {
                    /* 0xxxxxxx*/
                    count++
                    chars[chararr_count++] = c.toChar()
                }
                12, 13 -> {
                    /* 110x xxxx   10xx xxxx*/
                    count += 2
                    if (count > len)
                        throw UTFDataFormatException(
                                "malformed input: partial character at end")
                    char2 = bytes[count - 1].toInt()
                    if (char2 and 0xC0 != 0x80)
                        throw UTFDataFormatException(
                                "malformed input around byte " + count)
                    chars[chararr_count++] = (c and 0x1F shl 6 or (char2 and 0x3F)).toChar()
                }
                14 -> {
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3
                    if (count > len)
                        throw UTFDataFormatException(
                                "malformed input: partial character at end")
                    char2 = bytes[count - 2].toInt()
                    char3 = bytes[count - 1].toInt()
                    if (char2 and 0xC0 != 0x80 || char3 and 0xC0 != 0x80)
                        throw UTFDataFormatException(
                                "malformed input around byte " + (count - 1))
                    chars[chararr_count++] = (c and 0x0F shl 12 or
                            (char2 and 0x3F shl 6) or
                            (char3 and 0x3F)).toChar()
                }
                else ->
                    /* 10xx xxxx,  1111 xxxx */
                    throw UTFDataFormatException(
                            "malformed input around byte " + count)
            }
        }
        // The number of chars produced may be less than len
        return String(chars, 0, chararr_count)
    }

    // byte[]
    fun readBytes(n: Int): ByteArray {
        val bytes = ByteArray(n)
        buf.get(bytes)
        return bytes
    }

    fun skipBytes(n: Int) {
        for (i in 0 until n) {
            buf.get()
        }
    }
}