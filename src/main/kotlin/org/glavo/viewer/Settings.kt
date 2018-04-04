package org.glavo.viewer

import kala.nio.file.*
import org.glavo.viewer.util.*
import java.nio.file.Path

object Settings {
    val settingsPath: Path =
            (System.getProperty("viewer.settings.path")?.let { pathOf(it) }
                    ?: pathOf(System.getProperty("user.home")) / ".viewer").toAbsolutePath()

    val settingDataPath = (settingsPath / "settings.json").toAbsolutePath()

    var data: SettingData = run {
        if (settingDataPath.exists()) try {
            Logger.info("Loading Settings from ${settingDataPath}")
            GlobalGson.fromJson(settingDataPath.bufferedReader(), SettingData::class.java)
        } catch (e: Exception) {
            SettingData().apply {
                Logger.warning(exception = e)
            }
        }
        else {
            SettingData()
        }
    }.apply {
        Logger.info("Settings=$this")
    }

    val cssURL: String = (settingsPath / "viewer.css").apply {
        if (this.notExists()) {
            try {
                this.createFile().writeText(defaultCSS)
            } catch (e: Exception) {
                Logger.warning(exception = e)
            }
        }
    }.toURL().toExternalForm()

    init {
        ShutdownHook += {
            try {
                settingDataPath.writeText(GlobalGson.toJson(data))
            } catch (e: Exception) {
                Logger.warning(exception =  e)
            }
        }
        LoggerUtils.start()
    }
}