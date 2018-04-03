package org.glavo.viewer.util

import com.google.gson.GsonBuilder

val GlobalGson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .setLenient()
        .create()