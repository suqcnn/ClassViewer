package org.glavo.viewer.gui

import org.glavo.viewer.util.Logger

private val skins: Map<String, String> = mapOf(
        "modena" to "MODENA",
        "caspian" to "CASPIAN"
)

fun skinOf(name: String): String {
    return skins.entries.find { name.trim().toLowerCase() == name }?.value ?: run {
        Logger.warning("Unknown skin: $name")
        "MODENA"
    }
}