package org.glavo.kotlinfx.application

import javafx.application.Application
import javafx.stage.Stage

inline fun <reified T : Application> launch(args: Array<String>) {
    Application.launch(T::class.java, *args)
}

fun Application.run() {
    this.start(Stage())
}