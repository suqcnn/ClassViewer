@file:JvmName("Window")
package kotlinfx

typealias Window = javafx.stage.Window

typealias WindowEvent = javafx.stage.WindowEvent

fun Window.onCloseRequest(v: (WindowEvent) -> Unit) {
    this.onCloseRequest = v.asEventHandler()
}

fun Window.onHidden(v: (WindowEvent) -> Unit) {
    this.onHidden = v.asEventHandler()
}

fun Window.onHiding(v: (WindowEvent) -> Unit) {
    this.onHiding = v.asEventHandler()
}

fun Window.onShowing(v: (WindowEvent) -> Unit) {
    this.onShowing = v.asEventHandler()
}

fun Window.onShown(v: (WindowEvent) -> Unit) {
    this.onShown = v.asEventHandler()
}

