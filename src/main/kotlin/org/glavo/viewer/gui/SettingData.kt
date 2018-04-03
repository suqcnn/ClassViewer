package org.glavo.viewer.gui

import com.google.gson.annotations.SerializedName
import org.glavo.viewer.util.*

data class SettingData
@JvmOverloads
constructor(
        @SerializedName("logger.color")
        var colorLog: Boolean = false,

        @SerializedName("logger.debug")
        var debugLog: Boolean = false,


        @SerializedName("text.font.family")
        var textFontFamily: String? = defaultTextFontFamily,

        @SerializedName("text.font.size")
        var textFontSize: Double
)
