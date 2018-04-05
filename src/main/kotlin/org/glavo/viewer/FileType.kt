package org.glavo.viewer

import java.net.URI
import java.util.*

abstract class FileType {
    companion object {
        @JvmStatic
        val fileTypes: List<FileType> = ServiceLoader.load(FileType::class.java).toList()

        @JvmStatic
        fun valueOf(name: String): FileType = when (name) {
            "folder" -> TODO()
            "binary" -> TODO()
            "text" -> TODO()
            else -> {
                fileTypes.find { it.toString() == name } ?: TODO()
            }
        }
    }

    abstract fun accept(uri: URI): Boolean
}