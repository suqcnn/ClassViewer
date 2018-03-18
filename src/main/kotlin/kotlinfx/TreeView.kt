@file:JvmName("TreeView")

package kotlinfx

typealias TreeView<T> = javafx.scene.control.TreeView<T>

typealias TreeItem<T> = javafx.scene.control.TreeItem<T>

typealias TreeCell<T> = javafx.scene.control.TreeCell<T>

inline fun <T> treeView(root: TreeItem<T>? = null, initializer: TreeView<T>.() -> Unit = {}): TreeView<T> {
    return TreeView(root).apply(initializer)
}

inline fun <T> treeItem(value: T? = null, graphic: Node? = null, initializer: TreeItem<T>.() -> Unit = {}): TreeItem<T> {
    return TreeItem<T>(value, graphic).apply(initializer)
}

inline fun <T> treeCell(initializer: TreeCell<T>.() -> Unit = {}): TreeCell<T> {
    return TreeCell<T>().apply(initializer)
}