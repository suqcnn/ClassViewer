package org.glavo.viewer.gui

import javafx.application.Application
import javafx.beans.binding.Bindings
import javafx.beans.property.StringProperty
import javafx.collections.ObservableList
import javafx.scene.*
import javafx.stage.*
import javafx.scene.layout.*
import kotlinfx.application.launch
import kotlinfx.*
import kotlinfx.scene.*
import kotlinfx.stage.onCloseRequest
import kotlinfx.stringProperty
import org.glavo.viewer.*
import org.glavo.viewer.util.*
import java.util.concurrent.Callable

val icon16 by lazy { loadIcon("/icons/ui/viewer_16x16.png") }
val icon24 by lazy { loadIcon("/icons/ui/viewer_24x24.png") }
val icon32 by lazy { loadIcon("/icons/ui/viewer_32x32.png") }

internal val appInitializer: Unit by lazy {
    Application.setUserAgentStylesheet(skinOf(Settings.data.skin))
}

val viewerList: ObservableList<Viewer> = observableMutableListOf()

class Viewer : Application() {
    lateinit var stage: Stage

    val titleProperty: StringProperty = stringProperty(null)
    var title: String? by titleProperty

    init {
        viewerList += this
    }

    override fun start(stage: Stage) {
        this.stage = stage
        stage.apply {

            titleProperty().bind(Bindings.createStringBinding(
                    Callable {
                        if (this@Viewer.title == null)
                            Settings.data.title
                        else
                            "${Settings.data.title} - ${this@Viewer.title}"
                    },
                    this@Viewer.titleProperty
            ))
            icons.addAll(icon16, icon24, icon32)
            scene = scene(
                    width = Settings.data.width,
                    height = Settings.data.height,
                    root = ViewerPane(this@Viewer)
            ) {
                stylesheets += Settings.cssURL
                root.styleClass += UI_CLASS
            }

            onCloseRequest {
                viewerList.remove(this@Viewer)
            }
        }
        stage.show()
    }

    val scene: Scene
        inline get() = stage.scene

    val pane: ViewerPane
        inline get() = stage.scene.root as ViewerPane
}


fun main(args: Array<String>) {
    Settings
    launch<Viewer>(args)
}

