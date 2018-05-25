@file:Suppress("UNCHECKED_CAST")

package org.glavo.viewer.gui

import javafx.beans.property.BooleanProperty
import javafx.collections.ObservableList
import javafx.scene.control.TabPane
import kotlinfx.*

class ViewerTabPane(@JvmField val viewer: Viewer) : TabPane() {
    val viewerTabs: ObservableList<ViewerTab>
        inline get() = tabs as ObservableList<ViewerTab>

    val tab: ViewerTab?
        get() = selectionModel.selectedItem as ViewerTab?


    @get:JvmName("refreshableProperty")
    val refreshableProperty: BooleanProperty by lazy {
        booleanProperty().apply {
            selectionModelProperty().addListener { _, _, newValue ->
                val tab = newValue.selectedItem as ViewerTab?
                this.unbind()
                if (tab != null) {
                    this.bind(tab.refreshableProperty())
                } else {
                    this.set(false)
                }
            }
        }
    }

    var isRefreshable: Boolean by refreshableProperty
}