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

@JvmName("setOnScheduled")
fun Task<*>.onScheduled(v: (WorkerStateEvent) -> Unit) {
    this.onScheduled = v.asEventHandler()
}

fun <V> Task<V>.onScheduled(v: (V) -> Unit) {
    this.onScheduled = EventHandler { v(it.source.value as V) }
}

