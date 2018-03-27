@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

import javafx.collections.FXCollections

typealias ObservableArray<T> = javafx.collections.ObservableArray<T>

typealias ObservableFloatArray = javafx.collections.ObservableFloatArray

typealias ObservableIntegerArray = javafx.collections.ObservableIntegerArray

typealias ObservableList<E> = javafx.collections.ObservableList<E>

typealias ObservableSet<E> = javafx.collections.ObservableSet<E>

typealias ObservableMap<K, V> = javafx.collections.ObservableMap<K, V>

//
// ObservableList
//

fun <E> List<E>.observable(): ObservableList<E> = FXCollections.observableList(this)

inline fun <E> List<E>.observable(crossinline extractor: (E) -> Array<Observable>): ObservableList<E> {
    return FXCollections.observableList(this) { extractor(it) }
}

fun <E> observableListOf(): ObservableList<E> = FXCollections.emptyObservableList()

fun <E> observableListOf(e: E): ObservableList<E> = FXCollections.singletonObservableList(e)

fun <E> observableListOf(vararg items: E): ObservableList<E> = FXCollections.observableArrayList(*items)

fun <E> ObservableList<E>.synchronized(): ObservableList<E> = FXCollections.synchronizedObservableList(this)

inline fun <reified E> ObservableList<E>.checked(): ObservableList<E> = FXCollections.checkedObservableList(this, E::class.java)

fun <E> ObservableList<E>.unmodifiable(): ObservableList<E> = FXCollections.unmodifiableObservableList(this)

//
// ObservableMap
//

fun <K, V> Map<K, V>.observable(): ObservableMap<K, V> = FXCollections.observableMap(this)

fun <K, V> observableMapOf(vararg items: Pair<K, V>): ObservableMap<K, V> = mapOf(*items).observable()

//TODO