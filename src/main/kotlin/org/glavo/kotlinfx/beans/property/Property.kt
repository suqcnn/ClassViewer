package org.glavo.kotlinfx.beans.property

import javafx.beans.property.*
import javafx.beans.value.*
import kotlin.reflect.KProperty

operator fun <T> ObservableObjectValue<T>.getValue(thisRef: Any, property: KProperty<*>): T {
    return this.value
}

operator fun <T> WritableValue<T>.setValue(thisRef: Any, property: KProperty<*>, value: T) {
    this.value = value
}

operator fun ObservableBooleanValue.getValue(thisRef: Any, property: KProperty<*>): Boolean {
    return this.get()
}

operator fun WritableBooleanValue.setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
    this.set(value)
}

operator fun ObservableFloatValue.getValue(thisRef: Any, property: KProperty<*>): Float {
    return this.get()
}

operator fun WritableFloatValue.setValue(thisRef: Any, property: KProperty<*>, value: Float) {
    this.set(value)
}

operator fun ObservableDoubleValue.getValue(thisRef: Any, property: KProperty<*>): Double {
    return this.get()
}

operator fun WritableDoubleValue.setValue(thisRef: Any, property: KProperty<*>, value: Double) {
    this.set(value)
}

operator fun ObservableIntegerValue.getValue(thisRef: Any, property: KProperty<*>): Int {
    return this.get()
}

operator fun WritableIntegerValue.setValue(thisRef: Any, property: KProperty<*>, value: Int) {
    this.set(value)
}

