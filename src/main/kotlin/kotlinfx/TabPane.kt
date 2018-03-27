@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias TabPane = javafx.scene.control.TabPane

typealias Tab = javafx.scene.control.Tab

inline fun tabPane(vararg tabs: Tab, initializer: TabPane.() -> Unit = {}): TabPane {
    return TabPane(*tabs).apply(initializer)
}

@JvmName("tabPaneOfArray")
inline fun tabPane(tabs: Array<out Tab>, initializer: TabPane.() -> Unit = {}): TabPane {
    return TabPane(*tabs).apply(initializer)
}

inline fun tabPane(tabs: Collection<Tab>, initializer: TabPane.() -> Unit = {}): TabPane {
    return TabPane().apply { this.tabs.addAll(tabs) }.apply(initializer)
}

inline fun tab(text: String? = null, context: Node? = null, initializer: Tab.() -> Unit): Tab {
    return Tab(text, context).apply(initializer)
}