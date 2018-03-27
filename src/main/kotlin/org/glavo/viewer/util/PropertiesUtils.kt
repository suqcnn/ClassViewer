@file:JvmName("PropertiesUtils")

package org.glavo.viewer.util

import java.util.*

fun Properties.booleanProperty(key: String): Boolean? {
    val s = this.getProperty(key)?.trim()?.toLowerCase()
    return when (s) {
        "true" -> true
        "false" -> false
        else -> null
    }
}

fun Properties.integerProperty(key: String): Int? {
    val s = this.getProperty(key)?.trim()?.toLowerCase() ?: return null

    return when {
        s.startsWith("0b") -> s.substring(2).toIntOrNull(2)
        s.startsWith("0x") -> s.substring(2).toIntOrNull(16)
        else -> s.toIntOrNull()
    }
}

fun Properties.doubleProperty(key: String): Double? {
    val s = this.getProperty(key)?.trim() ?: return null

    return s.toDoubleOrNull()
}

fun Properties.stringProperty(key: String): String? {
    return this.getProperty(key, null)
}


inline fun Properties.booleanPropertyOrUpdate(key: String, defaultValue: () -> Boolean): Boolean {
    return booleanProperty(key) ?: defaultValue().also {
        this.setProperty(key, it.toString())
    }
}

fun Properties.booleanPropertyOrUpdate(key: String, defaultValue: Boolean): Boolean {
    return booleanProperty(key) ?: defaultValue.also {
        this.setProperty(key, it.toString())
    }
}

inline fun Properties.integerPropertyOrUpdate(key: String, defaultValue: () -> Int): Int {
    return integerProperty(key) ?: defaultValue().also {
        this.setProperty(key, it.toString())
    }
}

fun Properties.integerPropertyOrUpdate(key: String, defaultValue: Int): Int {
    return integerProperty(key) ?: defaultValue.also {
        this.setProperty(key, it.toString())
    }
}

fun Properties.doublePropertyOrUpdate(key: String): Double? {
    val s = this.getProperty(key)?.trim() ?: return null

    return s.toDoubleOrNull()
}

inline fun Properties.doublePropertyOrUpdate(key: String, defaultValue: () -> Double): Double {
    return doubleProperty(key) ?: defaultValue().also {
        this.setProperty(key, it.toString())
    }
}

fun Properties.doublePropertyOrUpdate(key: String, defaultValue: Double): Double {
    return doubleProperty(key) ?: defaultValue.also {
        this.setProperty(key, it.toString())
    }
}

inline fun Properties.stringPropertyOrUpdate(key: String, defaultValue: () -> String): String {
    return stringProperty(key) ?: defaultValue().also {
        this.setProperty(key, it)
    }
}

fun Properties.stringPropertyOrUpdate(key: String, defaultValue: String): String {
    return stringProperty(key) ?: defaultValue.also {
        this.setProperty(key, it)
    }
}