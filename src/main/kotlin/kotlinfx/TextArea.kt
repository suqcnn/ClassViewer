@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias TextArea = javafx.scene.control.TextArea

inline fun textArea(text: String = "", initializer: TextArea.() -> Unit = {}): TextArea {
    return TextArea(text).apply(initializer)
}