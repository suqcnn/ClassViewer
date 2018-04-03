package org.glavo.viewer.gui

import javafx.application.Application
import javafx.beans.binding.*
import javafx.beans.property.*
import javafx.scene.text.Font
import kotlinfx.*
import org.glavo.viewer.util.*
import java.nio.file.*
import java.util.*
import java.util.concurrent.Callable
import kotlin.collections.LinkedHashMap

object Settings {
    val viewerDataPath = run {
        System.getProperty("viewer.settings.path")?.let { Paths.get(it) }
                ?: Paths.get(System.getProperty("user.home")!!, ".viewer")
    }.apply {
        when {
            Files.notExists(this) -> Files.createDirectories(this)
            !Files.isDirectory(this) -> Files.delete(this)
        }
    }

    private val settingProperties: Properties = Properties().apply {
        val p = viewerDataPath.resolve("viewer.properties")
        if (Files.exists(p)) {
            Files.newInputStream(p).use(::load)
        }

        ShutdownHook += {
            store(Files.newOutputStream(p), "ClassViewer Settings")
        }
    }

    const val defaultSkin = Application.STYLESHEET_MODENA

    private val skins: LinkedHashMap<String, String> = linkedMapOf(
            "modena" to Application.STYLESHEET_MODENA,
            "caspian" to Application.STYLESHEET_CASPIAN
    )

    @get:JvmName("colorLogProperty")
    val colorLogProperty: BooleanProperty =
            booleanProperty().apply { onChange { _, _, newValue -> Logger.setting("viewer.logger.color", newValue) } }

    @get:JvmName("debugProperty")
    val debugProperty: BooleanProperty =
            booleanProperty().apply { onChange { _, _, newValue -> Logger.setting("viewer.logger.debug", newValue) } }

    @get:JvmName("useSystemTitleBarProperty")
    val useSystemTitleBarProperty: BooleanProperty =
            booleanProperty().apply { onChange { _, _, newValue -> Logger.setting("viewer.ui.useSystemTitleBar", newValue) } }

    @get:JvmName("uiFontFamilyProperty")
    val uiFontFamilyProperty: StringProperty =
            stringProperty(settingProperties.stringPropertyOrUpdate("viewer.ui.font.family", defaultUiFontFamily))

    @get:JvmName("uiFontSizeProperty")
    val uiFontSizeProperty: DoubleProperty =
            doubleProperty(settingProperties.doublePropertyOrUpdate("viewer.ui.font.size", 15.0))

    @get:JvmName("uiFontBinding")
    val uiFontBinding: ObjectBinding<Font> = Bindings.createObjectBinding(Callable {
        Font.font(uiFontFamily, uiFontSize).apply { Logger.setting("viewer.ui.font", this) }
    }, uiFontFamilyProperty, uiFontSizeProperty)

    @get:JvmName("textFontFamilyProperty")
    val textFontFamilyProperty: StringProperty =
            stringProperty(settingProperties.stringPropertyOrUpdate("viewer.text.font.family", defaultTextFontFamily))
    @get:JvmName("textFontSizeProperty")
    val textFontSizeProperty: DoubleProperty =
            doubleProperty(settingProperties.doublePropertyOrUpdate("viewer.text.font.size", 15.0))

    @get:JvmName("textFontBinding")
    val textFontBinding: ObjectBinding<Font> = Bindings.createObjectBinding(Callable {
        Font.font(textFontFamily, textFontSize).apply { Logger.setting("viewer.text.font", this) }
    }, textFontFamilyProperty, textFontSizeProperty)

    @get:JvmName("skinProperty")
    val skinProperty: StringProperty =
            stringProperty().apply {
                onChange { _, _, newValue ->
                    Application.setUserAgentStylesheet(
                            skins.entries.find { (k, _) ->
                                k.trim().toLowerCase() == newValue.trim().toLowerCase()
                            }?.value?.apply {
                                Logger.setting("viewer.ui.skin", newValue)
                            } ?: defaultSkin.apply { Logger.warning("unknown skin: $newValue") }
                    )
                }
            }

    @get:JvmName("defaultWidthProperty")
    val defaultWidthProperty: DoubleProperty =
            doubleProperty().apply { onChange { _, _, newValue -> Logger.setting("viewer.ui.defaultWidth", newValue) } }

    @get:JvmName("defaultHeightProperty")
    val defaultHeightProperty: DoubleProperty =
            doubleProperty().apply { onChange { _, _, newValue -> Logger.setting("viewer.ui.defaultHeight", newValue) } }

    @get:JvmName("baseTitleProperty")
    val baseTitleProperty: StringProperty =
            stringProperty().apply { onChange { _, _, newValue -> Logger.setting("viewer.ui.title", newValue) } }


    var colorLog: Boolean by colorLogProperty
    var debug: Boolean by debugProperty
    var useSystemTitleBar: Boolean by useSystemTitleBarProperty

    var uiFont: Font
        get() = uiFontBinding.value
        set(value) {
            uiFontFamily = value.family
            uiFontSize = value.size
        }
    var uiFontFamily: String by uiFontFamilyProperty
    var uiFontSize: Double by uiFontSizeProperty


    var textFont: Font
        get() = textFontBinding.value
        set(value) {
            textFontFamily = value.family
            textFontSize = value.size
        }
    var textFontFamily: String by textFontFamilyProperty
    var textFontSize: Double by textFontSizeProperty

    var defaultWidth: Double by defaultWidthProperty
    var defaultHeight: Double by defaultHeightProperty

    var skin: String by skinProperty

    var baseTitle: String by baseTitleProperty

    init {
        Logger.setting("viewer.settings.path", viewerDataPath)
        colorLogProperty.value = settingProperties.booleanPropertyOrUpdate("viewer.logger.color", false)
        debugProperty.value = settingProperties.booleanPropertyOrUpdate("viewer.logger.debug", false)
        useSystemTitleBarProperty.value = settingProperties.booleanPropertyOrUpdate("viewer.ui.useSystemTitleBar", true)
        uiFontBinding.value
        textFontBinding.value
        skinProperty.value = settingProperties.stringPropertyOrUpdate("viewer.ui.skin", defaultSkin)
        defaultWidthProperty.value = settingProperties.doublePropertyOrUpdate("viewer.ui.defaultWidth", 1150.0)
        defaultHeightProperty.value = settingProperties.doublePropertyOrUpdate("viewer.ui.defaultHeight", 725.0)
        baseTitleProperty.value = settingProperties.stringPropertyOrUpdate("viewer.ui.title", "ClassViewer")
    }

    fun init() {}
}

