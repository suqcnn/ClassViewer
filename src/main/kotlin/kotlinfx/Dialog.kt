@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias Dialog<R> = javafx.scene.control.Dialog<R>

inline fun <R> dialog(initializer: Dialog<R>.() -> Unit = {}): Dialog<R> {
    return Dialog<R>().apply(initializer)
}