@file:JvmName("TextField")

package kotlinfx

typealias TextField = javafx.scene.control.TextField

inline fun textField(text: String = "", initializer: TextField.() -> Unit = {}): TextField {
    return TextField(text).apply(initializer)
}