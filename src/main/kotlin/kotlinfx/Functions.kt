@file:JvmName("KotlinFX")
@file:JvmMultifileClass
@file:Suppress("NOTHING_TO_INLINE")

package kotlinfx

fun <T : Event> ((T) -> Unit).asEventHandler(): EventHandler<T> {
    return EventHandler { this(it) }
}

fun (() -> Unit).asRunnable(): Runnable = Runnable { this() }

