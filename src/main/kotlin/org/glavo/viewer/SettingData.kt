package org.glavo.viewer

import com.google.gson.annotations.SerializedName
import org.glavo.viewer.util.*

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
        var useSystemMenuBar: Boolean = isMac,

        @SerializedName("ui.width")
        var width: Double = 1150.0,

        @SerializedName("ui.height")
        var height: Double = 725.0,

        @SerializedName("ui.title")
        var title: String = "ClassViewer",

        @SerializedName("ui.skin")
        var skin: String = "MODENA"
)
