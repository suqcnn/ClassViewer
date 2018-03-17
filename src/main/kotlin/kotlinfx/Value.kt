package kotlinfx

import kotlin.reflect.KProperty

typealias ChangeListener<T> = javafx.beans.value.ChangeListener<T>

typealias ObservableValue<T> = javafx.beans.value.ObservableValue<T>

typealias ObservableBooleanValue = javafx.beans.value.ObservableBooleanValue
typealias ObservableFloatValue = javafx.beans.value.ObservableFloatValue
typealias ObservableDoubleValue = javafx.beans.value.ObservableDoubleValue
typealias ObservableIntegerValue = javafx.beans.value.ObservableIntegerValue
typealias ObservableLongValue = javafx.beans.value.ObservableLongValue
typealias ObservableNumberValue = javafx.beans.value.ObservableNumberValue
typealias ObservableStringValue = javafx.beans.value.ObservableStringValue
typealias ObservableObjectValue<T> = javafx.beans.value.ObservableObjectValue<T>

typealias ObservableListValue<E> = javafx.beans.value.ObservableListValue<E>
typealias ObservableSetValue<E> = javafx.beans.value.ObservableSetValue<E>
typealias ObservableMapValue<K, V> = javafx.beans.value.ObservableMapValue<K, V>


typealias WritableValue<T> = javafx.beans.value.WritableValue<T>

typealias WritableBooleanValue = javafx.beans.value.WritableBooleanValue
typealias WritableFloatValue = javafx.beans.value.WritableFloatValue
typealias WritableDoubleValue = javafx.beans.value.WritableDoubleValue
typealias WritableIntegerValue = javafx.beans.value.WritableIntegerValue
typealias WritableLongValue = javafx.beans.value.WritableLongValue
typealias WritableNumberValue = javafx.beans.value.WritableNumberValue
typealias WritableStringValue = javafx.beans.value.WritableStringValue
typealias WritableObjectValue<T> = javafx.beans.value.WritableObjectValue<T>

typealias WritableListValue<E> = javafx.beans.value.WritableListValue<E>
typealias WritableSetValue<E> = javafx.beans.value.WritableSetValue<E>

typealias WritableMapValue<K, V> = javafx.beans.value.WritableMapValue<K, V>


operator fun ObservableBooleanValue.getValue(thisRef: Any?, property: KProperty<*>): Boolean {
    return this.get()
}

operator fun ObservableFloatValue.getValue(thisRef: Any?, property: KProperty<*>): Float {
    return this.get()
}

operator fun ObservableDoubleValue.getValue(thisRef: Any?, property: KProperty<*>): Double {
    return this.get()
}

operator fun ObservableIntegerValue.getValue(thisRef: Any?, property: KProperty<*>): Int {
    return this.get()
}

operator fun ObservableLongValue.getValue(thisRef: Any?, property: KProperty<*>): Long {
    return this.get()
}

operator fun ObservableNumberValue.getValue(thisRef: Any?, property: KProperty<*>): Number {
    return this.value
}


operator fun <T> ObservableObjectValue<T>.getValue(thisRef: Any?, property: KProperty<*>): T {
    return this.get()
}


operator fun WritableBooleanValue.setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
    this.set(value)
}

operator fun WritableFloatValue.setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
    this.set(value)
}

operator fun WritableDoubleValue.setValue(thisRef: Any?, property: KProperty<*>, value: Double) {
    this.set(value)
}

operator fun WritableIntegerValue.setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
    this.set(value)
}

operator fun WritableLongValue.setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
    this.set(value)
}

operator fun <T> WritableObjectValue<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    this.set(value)
}
