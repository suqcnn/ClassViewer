@file:JvmName("Layout")

package kotlinfx

typealias Pane = javafx.scene.layout.Pane

typealias HBox = javafx.scene.layout.HBox

typealias VBox = javafx.scene.layout.VBox

typealias BorderPane = javafx.scene.layout.BorderPane

typealias FlowPane = javafx.scene.layout.FlowPane

inline fun pane(vararg children: Node, initializer: Pane.() -> Unit = {}): Pane {
    return Pane(*children).apply(initializer)
}

@JvmName("paneOfArray")
inline fun pane(children: Array<out Node>, initializer: Pane.() -> Unit = {}): Pane {
    return Pane(*children).apply(initializer)
}

inline fun pane(children: Collection<Node>, initializer: Pane.() -> Unit = {}): Pane {
    return Pane().apply { this.children.addAll(children) }.apply(initializer)
}

inline fun hbox(vararg children: Node, initializer: HBox.() -> Unit = {}): HBox {
    return HBox(*children).apply(initializer)
}

@JvmName("hboxOfArray")
inline fun hbox(children: Array<out Node>, initializer: HBox.() -> Unit = {}): HBox {
    return HBox(*children).apply(initializer)
}

inline fun hbox(children: Collection<Node>, initializer: HBox.() -> Unit = {}): HBox {
    return HBox().apply { this.children.addAll(children) }.apply(initializer)
}

inline fun vbox(vararg children: Node, initializer: VBox.() -> Unit = {}): VBox {
    return VBox(*children).apply(initializer)
}

@JvmName("vboxOfArray")
inline fun vbox(children: Array<out Node>, initializer: VBox.() -> Unit = {}): VBox {
    return VBox(*children).apply(initializer)
}

inline fun vbox(children: Collection<Node>, initializer: VBox.() -> Unit = {}): VBox {
    return VBox().apply { this.children.addAll(children) }.apply(initializer)
}

@JvmName("borderPaneOfArray")
inline fun borderPane(
        center: Node? = null,
        top: Node? = null,
        right: Node? = null,
        bottom: Node? = null,
        left: Node? = null,
        initializer: BorderPane.() -> Unit = {}): BorderPane {
    return BorderPane().apply {
        if (center != null)
            this.center = center

        if (top != null)
            this.top = top

        if (right != null)
            this.right = right

        if (bottom != null)
            this.bottom = bottom

        if (left != null)
            this.left = left
    }.apply(initializer)
}

inline fun flowPane(orientation: Orientation = Orientation.HORIZONTAL, vararg children: Node, initializer: FlowPane.() -> Unit = {}): FlowPane {
    return FlowPane(orientation, *children).apply(initializer)
}

@JvmName("flowPaneOfArray")
inline fun flowPane(orientation: Orientation = Orientation.HORIZONTAL, children: Array<out Node>, initializer: FlowPane.() -> Unit = {}): FlowPane {
    return FlowPane(orientation, *children).apply(initializer)
}

inline fun flowPane(orientation: Orientation = Orientation.HORIZONTAL, children: Collection<Node>, initializer: FlowPane.() -> Unit = {}): FlowPane {
    return FlowPane(orientation).apply { this.children.addAll(children) }.apply(initializer)
}

//todo