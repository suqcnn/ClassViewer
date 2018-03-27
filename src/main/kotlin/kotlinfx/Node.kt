@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias Node = javafx.scene.Node

typealias Parent = javafx.scene.Parent

inline fun Node.onContextMenuRequested(crossinline v: (ContextMenuEvent) -> Unit) {
    this.onContextMenuRequested = EventHandler { v(it) }
}

inline fun Node.onDragDetected(crossinline v: (MouseEvent) -> Unit) {
    this.onDragDetected = EventHandler { v(it) }
}

inline fun Node.onDragDone(crossinline v: (DragEvent) -> Unit) {
    this.onDragDone = EventHandler { v(it) }
}

inline fun Node.onDragDropped(crossinline v: (DragEvent) -> Unit) {
    this.onDragDropped = EventHandler { v(it) }
}

inline fun Node.onDragEntered(crossinline v: (DragEvent) -> Unit) {
    this.onDragEntered = EventHandler { v(it) }
}

inline fun Node.onDragExited(crossinline v: (DragEvent) -> Unit) {
    this.onDragExited = EventHandler { v(it) }
}

inline fun Node.onDragOver(crossinline v: (DragEvent) -> Unit) {
    this.onDragOver = EventHandler { v(it) }
}
