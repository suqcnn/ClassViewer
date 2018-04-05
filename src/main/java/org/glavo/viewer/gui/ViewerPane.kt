package org.glavo.viewer.gui

import javafx.scene.layout.BorderPane

class ViewerPane(val viewer: Viewer) : BorderPane() {
    init {
        top = ViewerTopBar(viewer)
    }

    val topBar: ViewerTopBar
        inline get() = this.top as ViewerTopBar
}