@file:JvmName("Label")

package kotlinfx

typealias Label = javafx.scene.control.Label

typealias Labeled = javafx.scene.control.Labeled

inline fun label(text: String = "", graphic: Node? = null, initializer: Label .() -> Unit = {}): Label {
    return Label(text).apply { if (graphic != null) this.graphic = graphic }.apply(initializer)
}