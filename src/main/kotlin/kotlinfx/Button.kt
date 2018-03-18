package kotlinfx

typealias Button = javafx.scene.control.Button

typealias ButtonBase = javafx.scene.control.ButtonBase

inline fun button(text: String = "", graphic: Node? = null, initializer: Button .() -> Unit = {}): Button {
    return Button(text).apply { if (graphic != null) this.graphic = graphic }.apply(initializer)
}

fun ButtonBase.onAction(v: (ActionEvent) -> Unit) {
    this.onAction = EventHandler { v(it) }
}