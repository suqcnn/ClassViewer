@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias ProgressBar = javafx.scene.control.ProgressBar

typealias ProgressIndicator = javafx.scene.control.ProgressIndicator

inline fun progressBar(progress: Double = ProgressIndicator.INDETERMINATE_PROGRESS, initializer: ProgressBar.() -> Unit = {}): ProgressBar {
    return ProgressBar(progress).apply(initializer)
}