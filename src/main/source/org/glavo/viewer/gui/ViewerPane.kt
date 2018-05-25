package org.glavo.viewer.gui

import javafx.scene.layout.BorderPane
import org.glavo.viewer.util.CssUtils

class ViewerPane(@JvmField val viewer: Viewer) : BorderPane() {
    init {
        this.styleClass.add(CssUtils.UI_CLASS)
        this.top = ViewerTopBar(viewer)
        this.center = ViewerTabPane(viewer)

        topBar.toolBar.refreshButton.disableProperty().bind(tabPane.refreshableProperty.not())
    }

    val topBar: ViewerTopBar
        inline get() = top as ViewerTopBar

    val tabPane: ViewerTabPane
        inline get() = center as ViewerTabPane
}

class ViewerCenterPane(@JvmField val viewer: Viewer) {

}