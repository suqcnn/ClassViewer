package kotlinfx

typealias Node = javafx.scene.Node

typealias Parent = javafx.scene.Parent

fun Node.onContextMenuRequested(v: (ContextMenuEvent) -> Unit) {
    this.onContextMenuRequested = EventHandler { v(it) }
}

fun Node.onDragDetected(v: (MouseEvent) -> Unit) {
    this.onDragDetected = EventHandler { v(it) }
}

fun Node.onDragDone(v: (DragEvent) -> Unit) {
    this.onDragDone = EventHandler { v(it) }
}

fun Node.onDragDropped(v: (DragEvent) -> Unit) {
    this.onDragDropped = EventHandler { v(it) }
}

fun Node.onDragEntered(v: (DragEvent) -> Unit) {
    this.onDragEntered = EventHandler { v(it) }
}

fun Node.onDragExited(v: (DragEvent) -> Unit) {
    this.onDragExited = EventHandler { v(it) }
}

fun Node.onDragOver(v: (DragEvent) -> Unit) {
    this.onDragOver = EventHandler { v(it) }
}


//todo