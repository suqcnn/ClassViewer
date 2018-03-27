package org.glavo.viewer

import java.net.URI
import java.util.*

abstract class FileType {
    companion object {
        @JvmStatic
        val fileTypes: List<FileType> = Collections.unmodifiableList(arrayListOf<FileType>().apply {
            ServiceLoader.load(FileType::class.java).forEach { this.add(it) }
        })
    }

    abstract fun accept(uri: URI): Boolean
}