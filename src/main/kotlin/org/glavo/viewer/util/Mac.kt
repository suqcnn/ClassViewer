package org.glavo.viewer.util

object Mac {
    val isMac: Boolean = System.getProperty("os.name", "") == "Mac OS X"

    init {
        if (isMac) {
            System.setProperty("apple.laf.useScreenMenuBar", "true")
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "ClassViewer")
        }
    }
}