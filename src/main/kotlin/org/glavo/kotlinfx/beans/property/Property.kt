package org.glavo.kotlinfx.beans.property

import javafx.beans.property.*
import javafx.collections.ObservableList
import kotlin.reflect.KProperty

operator fun <T> ReadOnlyObjectProperty<T>.getValue(thisRef: Any, property: KProperty<*>): T {
    return this.value
}

operator fun <T> ObjectProperty<T>.setValue(thisRef: Any, property: KProperty<*>, value: T) {
    this.value = value
}

operator fun ReadOnlyStringProperty.getValue(thisRef: Any, property: KProperty<*>): String? {
    return this.value
}

operator fun StringProperty.setValue(thisRef: Any, property: KProperty<*>, value: String?) {
    this.value = value
}

operator fun ReadOnlyBooleanProperty.getValue(thisRef: Any, property: KProperty<*>): Boolean {
    return this.get()
}

operator fun BooleanProperty.setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
    this.set(value)
}

operator fun ReadOnlyFloatProperty.getValue(thisRef: Any, property: KProperty<*>): Float {
    return this.get()
}

operator fun FloatProperty.setValue(thisRef: Any, property: KProperty<*>, value: Float) {
    this.set(value)
}

operator fun ReadOnlyDoubleProperty.getValue(thisRef: Any, property: KProperty<*>): Double {
    return this.get()
}

operator fun DoubleProperty.setValue(thisRef: Any, property: KProperty<*>, value: Double) {
    this.set(value)
}

operator fun ReadOnlyIntegerProperty.getValue(thisRef: Any, property: KProperty<*>): Int {
    return this.get()
}

operator fun IntegerProperty.setValue(thisRef: Any, property: KProperty<*>, value: Int) {
    this.set(value)
}

operator fun <T> ReadOnlyListProperty<T>.getValue(thisRef: Any, property: KProperty<*>): ObservableList<T> {
    return this.value
}

operator fun <T> ListProperty<T>.setValue(thisRef: Any, property: KProperty<*>, value: ObservableList<T>?) {
    this.value = value
}
