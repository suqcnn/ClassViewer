package kotlinfx

typealias Window = javafx.stage.Window

typealias WindowEvent = javafx.stage.WindowEvent

fun Window.onCloseRequest(v: (WindowEvent) -> Unit) {
    this.onCloseRequest = EventHandler { v(it) }
}

fun Window.onHidden(v: (WindowEvent) -> Unit) {
    this.onHidden = EventHandler { v(it) }
}

fun Window.onHiding(v: (WindowEvent) -> Unit) {
    this.onHiding = EventHandler { v(it) }
}

fun Window.onShowing(v: (WindowEvent) -> Unit) {
    this.onShowing = EventHandler { v(it) }
}

fun Window.onShown(v: (WindowEvent) -> Unit) {
    this.onShown = EventHandler { v(it) }
}

