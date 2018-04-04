package org.glavo.viewer.gui

import javafx.application.Application
import javafx.scene.*
import javafx.stage.*
import kotlinfx.application.launch
import kotlinfx.scene.scene
import org.glavo.viewer.Settings
import org.glavo.viewer.util.loadIcon

val icon16 = loadIcon("/icons/ui/viewer_16x16.png")
val icon24 = loadIcon("/icons/ui/viewer_24x24.png")
val icon32 = loadIcon("/icons/ui/viewer_32x32.png")

class Viewer : Application() {
    lateinit var stage: Stage
    lateinit var scene: Scene

    override fun start(stage: Stage) {
        this.stage = stage
        stage.apply {
            icons.addAll(icon16, icon24, icon32)
            scene = scene(width = Settings.data.width, height = Settings.data.width) {
                this@Viewer.scene = this

                enableDragAndDrop()
            }
        }
        stage.show()
    }


    private fun enableDragAndDrop() {
        //TODO
    }
}


fun main(args: Array<String>) {
    Settings
    launch<Viewer>(args)
}

