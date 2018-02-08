package org.glavo.kotlinfx.application

typealias Application = javafx.application.Application

inline fun <reified T : Application> launch(vararg args: String) {
    Application.launch(T::class.java, *args)
}

fun Application.run() {
    this.start(javafx.stage.Stage())
}