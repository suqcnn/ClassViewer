@file:JvmName("Concurrent")
@file:Suppress("UNCHECKED_CAST")

package kotlinfx

typealias ScheduledService<V> = javafx.concurrent.ScheduledService<V>

typealias Service<V> = javafx.concurrent.Service<V>

typealias Task<V> = javafx.concurrent.Task<V>

typealias Worker<V> = javafx.concurrent.Worker<V>

typealias WorkerStateEvent = javafx.concurrent.WorkerStateEvent

inline fun <V> task(crossinline f: () -> V): Task<V> {
    return object : Task<V>() {
        override fun call(): V = f()
    }
}

fun Task<*>.startInNewThread() {
    Thread(this).start()
}

@JvmName("setOnSucceeded")
inline fun Task<*>.onSucceeded(crossinline v: (WorkerStateEvent) -> Unit) {
    this.onSucceeded = EventHandler { v(it) }
}

inline fun <V> Task<V>.onSucceeded(crossinline v: (V) -> Unit) {
    this.onSucceeded = EventHandler { v(it.source.value as V) }
}

@JvmName("setOnFailed")
inline fun Task<*>.onFailed(crossinline v: (WorkerStateEvent) -> Unit) {
    this.onSucceeded = EventHandler { v(it) }
}

inline fun Task<*>.onFailed(crossinline v: (Throwable) -> Unit) {
    this.onSucceeded = EventHandler { v(it.source.exception) }
}