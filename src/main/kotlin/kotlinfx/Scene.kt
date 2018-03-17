@file:JvmName("Scene")

package kotlinfx

import javafx.scene.Parent
import javafx.scene.layout.Pane

typealias Scene = javafx.scene.Scene

typealias SceneAntialiasing = javafx.scene.SceneAntialiasing

inline fun scene(
        root: Parent = Group(),
        width: Double = -1.0,
        height: Double = -1.0,
        depthBuffer: Boolean = false,
        antiAliasing: SceneAntialiasing = SceneAntialiasing.DISABLED,
        initializer: Scene.() -> Unit = {}): Scene {
    return Scene(root, width, height, depthBuffer, antiAliasing).apply { initializer() }
}

val Scene.children: ObservableList<Node>
    get() {
        val r = root
        return when (r) {
            is Group -> r.children
            is Pane -> r.children
            else -> throw IllegalStateException()
        }
    }

var Scene.contextValue: Node
    get() = children[0]
    set(value) {
        context = listOf(value)
    }


var Scene.context: Collection<Node>
    get() = children
    set(value) {
        children.setAll(value)
    }
