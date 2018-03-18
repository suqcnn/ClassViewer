@file:JvmName("Collections")

package kotlinfx

import javafx.collections.FXCollections

typealias ObservableArray<T> = javafx.collections.ObservableArray<T>

typealias ObservableFloatArray = javafx.collections.ObservableFloatArray

typealias ObservableIntegerArray = javafx.collections.ObservableIntegerArray

typealias ObservableList<E> = javafx.collections.ObservableList<E>

typealias ObservableSet<E> = javafx.collections.ObservableSet<E>

typealias ObservableMap<K, V> = javafx.collections.ObservableMap<K, V>

fun <E> List<E>.observable(): ObservableList<E> = FXCollections.observableList(this)

fun <E> observableListOf(vararg items: E): ObservableList<E> = FXCollections.observableArrayList(*items)