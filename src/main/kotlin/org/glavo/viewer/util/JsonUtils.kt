package org.glavo.viewer.util

import com.google.gson.GsonBuilder
import org.glavo.viewer.RecentFile
import org.glavo.viewer.RecentFileDeserializer
import org.glavo.viewer.RecentFileSerializer

val GlobalGson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .setLenient()
        .registerTypeAdapter(RecentFile::class.java, RecentFileSerializer)
        .registerTypeAdapter(RecentFile::class.java, RecentFileDeserializer)
        .create()