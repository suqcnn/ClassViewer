@file:JvmName("UrlUtils")

package org.glavo.viewer.util

import java.net.URL
import java.net.URLDecoder
import java.nio.file.Path

fun URL.readData(): ByteArray {
    return this.readBytes()
}

val URL.fileName: String
    get() = this.toString().split("/").last()

val URL.className: String?
    get() {
        val name = this.fileName
        return if (fileName.endsWith(".class", ignoreCase = true)) {
            name.substring(0, name.length - 6)
        } else {
            null
        }
    }

@Suppress("DEPRECATION")
@JvmName("pathToUrl")
fun Path.toUrl(): URL {
    return URL(URLDecoder.decode(this.toUri().toString()))
}