package org.glavo.viewer.gui

import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.input.MouseButton
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import kotlinfx.scene.*
import kotlinfx.scene.control.*
import org.glavo.viewer.Settings
import org.glavo.viewer.util.UI_CLASS
import org.glavo.viewer.util.loadIcon
import java.util.*

val CloseActiveIcon = loadIcon("/icons/ui/closeActive.png")
val MaximizeIcon = loadIcon("/icons/ui/maximize.png")
val MinimizeIcon = loadIcon("/icons/ui/minimize.png")
val RestoreIcon = loadIcon("/icons/ui/restore.png")

val titleBarResource: ResourceBundle = ResourceBundle.getBundle("org.glavo.viewer.gui.TitleBar")
val menuResource: ResourceBundle = ResourceBundle.getBundle("org.glavo.viewer.gui.Menu")

fun Viewer.buildTopBar(): Node {
    val titleBar = if (Settings.data.useSystemTitleBar) null else buildTitleBar()
    val menuBar = if (Settings.data.useSystemMenuBar) null else buildMenuBar()
    TODO()
}

private fun Viewer.buildTitleBar(): ToolBar {
    val viewer = this

    val titleLabel = label()

    val logoView = icon24.createView()
    val closeActiveButton = button(graphic = CloseActiveIcon.createView()) { styleClass += UI_CLASS }
    val maximizeButton = button(graphic = MaximizeIcon.createView()) { styleClass += UI_CLASS }
    val minimizeButton = button(graphic = MinimizeIcon.createView()) { styleClass += UI_CLASS }
    val restoreButton = button(graphic = RestoreIcon.createView()) { styleClass += UI_CLASS }

    closeActiveButton.tooltip = tooltip(titleBarResource.getString("CloseActiveButton.tooltip"))
    maximizeButton.tooltip = tooltip(titleBarResource.getString("MaximizeButton.tooltip"))
    minimizeButton.tooltip = tooltip(titleBarResource.getString("MinimizeButton.tooltip"))
    restoreButton.tooltip = tooltip(titleBarResource.getString("RestoreButton.tooltip"))

    return toolBar {
        styleClass += UI_CLASS
        var xOffset = 0.0
        var yOffset = 0.0

        onMouseClicked {
            if (it.button == MouseButton.PRIMARY && it.clickCount == 2) {
                viewer.stage.isMaximized = !viewer.stage.isMaximized
            }
        }

        onMousePressed {
            if (it.button == MouseButton.PRIMARY) {
                xOffset = it.sceneX
                yOffset = it.sceneY
            }
        }

        onMouseDragged {
            if (it.button == MouseButton.PRIMARY) {
                viewer.stage.apply {
                    x = it.screenX - xOffset
                    y = it.screenY - yOffset
                }
            }
        }

        titleLabel.textProperty().bind(viewer.stage.titleProperty())
        viewer.stage.maximizedProperty().addListener { _, _, newValue ->
            if (newValue) {
                items[5] = restoreButton
            } else {
                items[5] = maximizeButton
            }
        }

        minimizeButton.onAction { viewer.stage.isIconified = true }
        maximizeButton.onAction { viewer.stage.isMaximized = true }
        restoreButton.onAction { viewer.stage.isMaximized = false }
        closeActiveButton.onAction { viewer.stage.close() }

        val noop1 = text(" ") { styleClass += UI_CLASS }
        val noop2 = hbox()

        HBox.setHgrow(noop1, Priority.NEVER)
        HBox.setHgrow(noop2, Priority.ALWAYS)

        HBox.setHgrow(logoView, Priority.NEVER)
        HBox.setHgrow(minimizeButton, Priority.NEVER)
        HBox.setHgrow(maximizeButton, Priority.NEVER)
        HBox.setHgrow(restoreButton, Priority.NEVER)
        HBox.setHgrow(closeActiveButton, Priority.NEVER)

        items.addAll(logoView, noop1, titleLabel, noop2, minimizeButton,
                if (stage.isMaximized)
                    restoreButton
                else
                    maximizeButton,
                closeActiveButton)
    }
}

private fun Viewer.buildMenuBar(): MenuBar {
    val viewer = this

    TODO()
}