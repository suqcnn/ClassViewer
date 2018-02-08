package org.glavo.kotlinfx.beans.property

import javafx.beans.property.*
import javafx.beans.value.ObservableValue
import javafx.beans.value.WritableValue
import kotlin.reflect.KProperty

operator fun <T> ObservableValue<T>.getValue(thisRef: Any, property: KProperty<*>): T {
    return this.value
}

operator fun <T> WritableValue<T>.setValue(thisRef: Any, property: KProperty<*>, value: T) {
    this.value = value
}