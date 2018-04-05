package org.glavo.viewer.gui

import javafx.application.Application
import javafx.collections.ObservableList
import javafx.scene.*
import javafx.stage.*
import javafx.scene.layout.*
import kotlinfx.application.launch
import kotlinfx.observableMutableListOf
import kotlinfx.scene.*
import kotlinfx.stage.onCloseRequest
import org.glavo.viewer.*
import org.glavo.viewer.util.*

val icon16 by lazy { loadIcon("/icons/ui/viewer_16x16.png") }
val icon24 by lazy { loadIcon("/icons/ui/viewer_24x24.png") }
val icon32 by lazy { loadIcon("/icons/ui/viewer_32x32.png") }

internal val appInitializer: Unit by lazy {
    Application.setUserAgentStylesheet(skinOf(Settings.data.skin))
}

val viewerList: ObservableList<Viewer> = observableMutableListOf()

class Viewer : Application() {
    lateinit var stage: Stage
    lateinit var pane: BorderPane

    init {
        viewerList += this
    }

    override fun start(stage: Stage) {
        this.stage = stage
        stage.apply {
            icons.addAll(icon16, icon24, icon32)
            this@Viewer.pane = borderPane(
                    top = ViewerTopBar(this@Viewer)
            )
            scene = scene(
                    width = Settings.data.width,
                    height = Settings.data.height,
                    root = pane
            )

            onCloseRequest {
                viewerList.remove(this@Viewer)
            }
        }
        stage.show()
    }

    var scene: Scene
        inline get() = stage.scene
        inline set(value) {
            stage.scene = value
        }
}


fun main(args: Array<String>) {
    Settings
    launch<Viewer>(args)
}

