@file:JvmName("Menu")

package kotlinfx

typealias MenuBar = javafx.scene.control.MenuBar

typealias MenuItem = javafx.scene.control.MenuItem

typealias Menu = javafx.scene.control.Menu

typealias MenuButton = javafx.scene.control.MenuButton

typealias CustomMenuItem = javafx.scene.control.CustomMenuItem

typealias SeparatorMenuItem = javafx.scene.control.SeparatorMenuItem

inline fun menuBar(vararg menus: Menu, initializer: MenuBar.() -> Unit = {}): MenuBar {
    return MenuBar(*menus).apply(initializer)
}

@JvmName("menuBarOfArray")
inline fun menuBar(menus: Array<out Menu>, initializer: MenuBar.() -> Unit = {}): MenuBar {
    return MenuBar(*menus).apply(initializer)
}

inline fun menuBar(menus: Collection<Menu>, initializer: MenuBar.() -> Unit = {}): MenuBar {
    return MenuBar().apply { this.menus.addAll(menus) }.apply(initializer)
}


inline fun menuItem(text: String = "", graphic: Node? = null, initializer: MenuItem .() -> Unit = {}): MenuItem {
    return MenuItem(text, graphic).apply(initializer)
}

inline fun menu(text: String = "", graphic: Node? = null, vararg items: MenuItem, initializer: Menu.() -> Unit = {}): Menu {
    return Menu(text, graphic, *items).apply(initializer)
}

@JvmName("menuOfArray")
inline fun menu(text: String = "", graphic: Node? = null, items: Array<out MenuItem>, initializer: Menu.() -> Unit = {}): Menu {
    return Menu(text, graphic, *items).apply(initializer)
}

inline fun menu(text: String = "", graphic: Node? = null, items: Collection<MenuItem>, initializer: Menu.() -> Unit = {}): Menu {
    return Menu(text, graphic).apply { this.items.addAll(items) }.apply(initializer)
}

inline fun menuButton(text: String = "", graphic: Node? = null, vararg items: MenuItem, initializer: MenuButton.() -> Unit = {}): MenuButton {
    return MenuButton(text, graphic, *items).apply(initializer)
}

@JvmName("menuButtonOfArray")
inline fun menuButton(text: String = "", graphic: Node? = null, items: Array<out MenuItem>, initializer: MenuButton.() -> Unit = {}): MenuButton {
    return MenuButton(text, graphic, *items).apply(initializer)
}

inline fun menuButton(text: String = "", graphic: Node? = null, items: Collection<MenuItem>, initializer: MenuButton.() -> Unit = {}): MenuButton {
    return MenuButton(text, graphic).apply { this.items.addAll(items) }.apply(initializer)
}

inline fun customMenuItem(node: Node? = null, hideOnClick: Boolean = true, initializer: CustomMenuItem.() -> Unit = {}): CustomMenuItem {
    return CustomMenuItem(node, hideOnClick).apply(initializer)
}

inline fun separatorMenuItem(initializer: SeparatorMenuItem.() -> Unit = {}): SeparatorMenuItem {
    return SeparatorMenuItem().apply(initializer)
}