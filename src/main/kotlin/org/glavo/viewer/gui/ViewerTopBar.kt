package org.glavo.viewer.gui

import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.collections.ListChangeListener
import javafx.collections.WeakListChangeListener
import javafx.scene.control.*
import javafx.scene.input.KeyCombination
import javafx.scene.input.MouseButton
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import kotlinfx.scene.*
import kotlinfx.scene.control.*
import kotlinfx.stage.stage
import org.glavo.viewer.Settings
import org.glavo.viewer.util.UI_CLASS
import org.glavo.viewer.util.loadIcon
import java.beans.EventHandler
import java.util.*
import java.util.concurrent.Callable

class ViewerTopBar(val viewer: Viewer) : VBox() {
    val titleBar: ViewerTitleBar? = if (Settings.data.useSystemTitleBar) null else ViewerTitleBar(viewer)
    val menuBar = ViewerMenuBar(viewer)

    init {
        titleBar?.also { this.children += it }
        this.children += menuBar
        Platform.runLater {
            menuBar.isUseSystemMenuBar = Settings.data.useSystemMenuBar
        }
    }

}

class ViewerTitleBar(val viewer: Viewer) : ToolBar() {
    companion object {
        val resource: ResourceBundle = ResourceBundle.getBundle("org.glavo.viewer.gui.TitleBar")

        val CloseActiveIcon = loadIcon("/icons/ui/closeActive.png")
        val MaximizeIcon = loadIcon("/icons/ui/maximize.png")
        val MinimizeIcon = loadIcon("/icons/ui/minimize.png")
        val RestoreIcon = loadIcon("/icons/ui/restore.png")
    }

    val logoView = icon24.createView()
    val titleLabel = label()

    val closeActiveButton = button(graphic = CloseActiveIcon.createView())
    val maximizeButton = button(graphic = MaximizeIcon.createView())
    val minimizeButton = button(graphic = MinimizeIcon.createView())
    val restoreButton = button(graphic = RestoreIcon.createView())

    var xOffset = 0.0
    var yOffset = 0.0

    init {
        closeActiveButton.tooltip = tooltip(resource.getString("CloseActiveButton.tooltip"))
        maximizeButton.tooltip = tooltip(resource.getString("MaximizeButton.tooltip"))
        minimizeButton.tooltip = tooltip(resource.getString("MinimizeButton.tooltip"))
        restoreButton.tooltip = tooltip(resource.getString("RestoreButton.tooltip"))

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

        val noop1 = text(" ")
        val noop2 = hbox()

        HBox.setHgrow(noop1, Priority.NEVER)
        HBox.setHgrow(noop2, Priority.ALWAYS)

        HBox.setHgrow(logoView, Priority.NEVER)
        HBox.setHgrow(minimizeButton, Priority.NEVER)
        HBox.setHgrow(maximizeButton, Priority.NEVER)
        HBox.setHgrow(restoreButton, Priority.NEVER)
        HBox.setHgrow(closeActiveButton, Priority.NEVER)

        items.addAll(logoView, noop1, titleLabel, noop2, minimizeButton,
                if (viewer.stage.isMaximized)
                    restoreButton
                else
                    maximizeButton,
                closeActiveButton)

    }
}

class ViewerMenuBar(val viewer: Viewer) : MenuBar() {
    companion object {
        val resource: ResourceBundle = ResourceBundle.getBundle("org.glavo.viewer.gui.Menu")

        val OpenFileIcon = loadIcon("/icons/actions/openFile.png")
        val OpenFolderIcon = loadIcon("/icons/actions/openFolder.png")

        val BulletIcon = loadIcon("/icons/ui/bullet.png")

        val RefreshIcon = loadIcon("/icons/actions/refresh.png")
    }

    inner class FileMenu : Menu(resource.getString("FileMenu.text")) {
        val openFileItem =
                menuItem(resource.getString("FileMenu.OpenFileItem.text"), OpenFileIcon.createView()) {
                    accelerator = KeyCombination.keyCombination("Shortcut+O")
                }

        val openFolderItem =
                menuItem(resource.getString("FileMenu.OpenFolderItem.text"), OpenFolderIcon.createView()) {
                    accelerator = KeyCombination.keyCombination("Shortcut+K")
                }

        val exitItem =
                menuItem(resource.getString("FileMenu.ExitItem.text")) {

                }

        init {
            exitItem.onAction { Platform.exit() }
            this.items.addAll(
                    openFileItem,
                    openFolderItem,
                    separatorMenuItem(),
                    exitItem
            )
        }
    }

    inner class WindowMenu : Menu(resource.getString("WindowMenu.text")) {
        private inner class WindowItem(val v: Viewer) : MenuItem() {
            init {
                textProperty().bind(Bindings.createStringBinding(Callable {
                    if (v.title == null) {
                        resource.getString("WindowMenu.Windows.emptyText")
                    } else {
                        v.title
                    }
                }, v.titleProperty))
                if (v === viewer) {
                    graphic = BulletIcon.createView()
                } else {
                    onAction { v.stage.toFront() }
                }
            }
        }

        val newWindowItem =
                menuItem(resource.getString("WindowMenu.NewWindowItem.text")) {
                    accelerator = KeyCombination.keyCombination("Shortcut+N")
                    onAction {
                        Platform.runLater { Viewer().start(stage()) }
                    }
                }

        val nextWindowItem =
                menuItem(resource.getString("WindowMenu.NextWindowItem.text")) {
                    accelerator = KeyCombination.keyCombination("Shift+Shortcut+[")
                    onAction {
                        val size = viewerList.size
                        if (size != 0 && size != 1) {
                            val idx = viewerList.indexOf(viewer)
                            if (idx == viewerList.size - 1) {
                                viewerList.firstOrNull()?.stage?.toFront()
                            } else {
                                viewerList[idx + 1].stage.toFront()
                            }
                        }
                    }
                }

        val previousWindowItem =
                menuItem(resource.getString("WindowMenu.PreviousWindowItem.text")) {
                    accelerator = KeyCombination.keyCombination("Shift+Shortcut+]")
                    onAction {
                        val size = viewerList.size
                        if (size != 0 && size != 1) {
                            val idx = viewerList.indexOf(viewer)
                            if (idx == 0) {
                                viewerList.lastOrNull()?.stage?.toFront()
                            } else {
                                viewerList[idx - 1].stage.toFront()
                            }
                        }
                    }
                }

        private val base = listOf(
                newWindowItem,
                separatorMenuItem(),
                nextWindowItem,
                previousWindowItem,
                separatorMenuItem()
        )

        private fun update() {
            items.setAll(base)
            items += viewerList.map { WindowItem(it) }
        }

        val listener = ListChangeListener<Viewer> {
            update()
        }

        init {
            viewerList.addListener(WeakListChangeListener(listener))
            update()
        }
    }

    val fileMenu = FileMenu()
    val windowMenu = WindowMenu()

    init {
        menus.addAll(fileMenu, windowMenu)
    }
}