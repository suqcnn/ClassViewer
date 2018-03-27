@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias Application = javafx.application.Application

typealias ConditionalFeature = javafx.application.ConditionalFeature

typealias HostServices = javafx.application.HostServices

typealias Preloader = javafx.application.Preloader

typealias JFXPlatform = javafx.application.Platform

inline fun <reified T : Application> launch(vararg args: String) = Application.launch(T::class.java, *args)

@JvmName("launchWithArrayArgs")
inline fun <reified T : Application> launch(args: Array<String>) = Application.launch(T::class.java, *args)

fun Array<String>.runApp(
        autoShow: Boolean = true,
        onInit: () -> Unit = {},
        onStop: () -> Unit = {},
        stageInitializer: Stage.() -> Unit) {
    App.run(this, autoShow, onInit, onStop, stageInitializer)
}