package org.glavo.viewer

import com.google.gson.annotations.SerializedName
import org.glavo.viewer.util.*
import java.awt.GraphicsEnvironment

internal val defaultWidth = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .defaultScreenDevice
        .displayMode
        .width / 11 * 6

internal val defaultHeight = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .defaultScreenDevice
        .displayMode
        .height / 5 * 3

data class SettingData
@JvmOverloads
constructor(
        @SerializedName("logger.color")
        var colorLog: Boolean = false,

        @SerializedName("logger.debug")
        var debugLog: Boolean = false,

        @SerializedName("ui.useSystemTitleBar")
        var useSystemTitleBar: Boolean = true,

        @SerializedName("ui.useSystemMenuBar")
        var useSystemMenuBar: Boolean = true,

        @SerializedName("ui.width")
        var width: Double = defaultWidth.toDouble(),

        @SerializedName("ui.height")
        var height: Double = defaultHeight.toDouble(),

        @SerializedName("ui.title")
        var title: String = "ClassViewer",

        @SerializedName("ui.skin")
        var skin: String = "MODENA"
)
