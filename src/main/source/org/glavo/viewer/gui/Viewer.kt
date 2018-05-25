package org.glavo.viewer.gui

import javafx.application.Application
import javafx.beans.binding.Bindings
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import kotlinfx.*
import org.glavo.viewer.Settings
import org.glavo.viewer.util.ImageUtils
import java.util.concurrent.Callable

class Viewer : Application() {
    companion object {
        @JvmStatic
        @get:JvmName("viewers")
        val viewers: ObservableList<Viewer> = FXCollections.observableArrayList()

        @JvmStatic
        @get:JvmName("logo16")
        val logo16: Image = ImageUtils.loadImage("/icons/ui/viewer_16x16.png")

        @JvmStatic
        @get:JvmName("logo24")
        val logo24: Image = ImageUtils.loadImage("/icons/ui/viewer_24x24.png")

        @JvmStatic
        @get:JvmName("logo32")
        val logo32: Image = ImageUtils.loadImage("/icons/ui/viewer_32x32.png")

        @JvmStatic
        @get:JvmName("logo128")
        val logo128: Image = ImageUtils.loadImage("/icons/ui/viewer_128x128.png")

        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Viewer::class.java, *args)
        }
    }

    @get:JvmName("titleProperty")
    val titleProperty: StringProperty = stringProperty()

    var title: String? by titleProperty

    lateinit var stage: Stage


    val scene: Scene
        get() = stage.scene
    val pane: ViewerPane
        get() = scene.root as ViewerPane

    override fun start(stage: Stage) {
        this.stage = stage.apply {
            onShowing = EventHandler { viewers += this@Viewer }
            onCloseRequest = EventHandler { viewers -= this@Viewer }

            titleProperty().bind(Bindings.createStringBinding(Callable {
                if (this@Viewer.title == null)
                    Settings.getData().title
                else
                    Settings.getData().title + " " + this.title
            }, this@Viewer.titleProperty))
            icons.addAll(logo16, logo32)
            scene = Scene(ViewerPane(this@Viewer), Settings.getWidth(), Settings.getHeight()).apply {
                stylesheets += Settings.getCssURL()
            }
            isMaximized = true
            show()
        }
    }


}