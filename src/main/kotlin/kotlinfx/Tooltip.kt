@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias Tooltip = javafx.scene.control.Tooltip


inline fun tooltip(text: String, initializer: Tooltip .() -> Unit = {}): Tooltip {
    return Tooltip(text).apply(initializer)
}