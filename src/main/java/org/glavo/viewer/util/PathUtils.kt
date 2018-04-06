@file:JvmName("UrlUtils")

package org.glavo.viewer.util

import java.net.URI
import java.net.URL
import java.net.URLDecoder
import java.nio.file.*

fun URL.readData(): ByteArray {
    return this.readBytes()
}

fun URI.readData(): ByteArray {
    return this.toURL().readBytes()
}

val URL.fileName: String
    get() = this.toString().split("/").last()

val URI.fileName: String
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

@JvmName("pathToURI")
fun Path.toURI(): URI {
    return URI(URLDecoder.decode(this.toUri().toString(), "UTF-8"))
}

@JvmName("pathToURL")
fun Path.toURL(): URL {
    return URL(URLDecoder.decode(this.toUri().toString(), "UTF-8"))
}

@JvmName("uriToPath")
fun URI.toPath(): Path? {
    return when (this.scheme) {
        "jar" -> {
            try {
                FileSystems.getFileSystem(this)
                Paths.get(this)
            } catch (e: FileSystemNotFoundException) {
                try {
                    FileSystems.newFileSystem(this, mapOf<String, Any>())
                    Paths.get(this)
                } catch (_: FileSystemNotFoundException) {
                    null
                } catch (_: FileSystemAlreadyExistsException) {
                    null
                }
            }
        }
        else -> try {
            Paths.get(this)
        } catch (_: FileSystemNotFoundException) {
            null
        }
    }
}

@JvmName("urlToPath")
fun URL.toPath(): Path? = this.toURI().toPath()