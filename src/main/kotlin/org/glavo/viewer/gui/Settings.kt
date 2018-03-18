package org.glavo.viewer.gui

import kotlinfx.*

object Settings {
    @get:JvmName("coloredLogProperty")
    val coloredLogProperty: BooleanProperty = SimpleBooleanProperty(true)

    @get:JvmName("useSystemTitleBarProperty")
    val useSystemTitleBarProperty: BooleanProperty = SimpleBooleanProperty(false)

    @get:JvmName("debugProperty")
    val debugProperty: BooleanProperty = SimpleBooleanProperty(false)

    @get:JvmName("uiFontProperty")
    val uiFontProperty: ObjectProperty<Font> = SimpleObjectProperty()

    var coloredLog: Boolean by coloredLogProperty
    var useSystemTitleBar: Boolean by useSystemTitleBarProperty
    var debug: Boolean by debugProperty

    init {

    }
}