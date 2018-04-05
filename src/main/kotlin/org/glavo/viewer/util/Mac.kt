package org.glavo.viewer.util

import org.glavo.viewer.Settings

val isMac: Boolean = System.getProperty("os.name", "") == "Mac OS X"

object Mac {
    val init by lazy {
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", Settings.data.title)
        System.setProperty("apple.awt.application.name", Settings.data.title)
        Unit
    }
}