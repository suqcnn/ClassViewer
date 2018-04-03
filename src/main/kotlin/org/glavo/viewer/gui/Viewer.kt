package org.glavo.viewer.gui

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle
import kotlinfx.*
import kotlinfx.application.launch
import kotlinfx.scene.scene

class Viewer : Application() {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Settings.init()
            launch<Viewer>(args)
        }
    }

    lateinit var stage: Stage
    lateinit var scene: Scene

    private val subscriptions: MutableList<Subscription> = arrayListOf()

    override fun init() {

    }

    override fun start(stage: Stage) {
        this.stage = stage
        stage.apply {
            if (!Settings.useSystemTitleBar) initStyle(StageStyle.UNDECORATED)

            scene = scene(width = Settings.defaultWidth, height = Settings.defaultHeight) {
                this@Viewer.scene = this
                enableDragAndDrop()
            }
        }

        stage.show()
    }

    override fun stop() {
        subscriptions.apply { forEach(Subscription::cancel) }.clear()
    }

    private fun enableDragAndDrop() {
        //TODO
    }
}