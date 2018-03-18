@file:JvmName("Dialog")

package kotlinfx

typealias Dialog<R> = javafx.scene.control.Dialog<R>

inline fun <R> dialog(initializer: Dialog<R>.() -> Unit = {}): Dialog<R> {
    return Dialog<R>().apply(initializer)
}