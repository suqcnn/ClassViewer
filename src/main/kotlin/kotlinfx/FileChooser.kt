@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias FileChooser = javafx.stage.FileChooser

typealias DirectoryChooser = javafx.stage.DirectoryChooser

inline fun fileChooser(initializer: FileChooser.() -> Unit = {}): FileChooser {
    return FileChooser().apply(initializer)
}

inline fun directoryChooser(initializer: DirectoryChooser.() -> Unit = {}): DirectoryChooser {
    return DirectoryChooser().apply(initializer)
}