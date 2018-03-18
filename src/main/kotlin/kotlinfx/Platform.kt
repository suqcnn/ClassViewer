package kotlinfx

@Suppress("NOTHING_TO_INLINE")
object Platform {
    @JvmStatic
    inline fun runLater(crossinline f: () -> Unit) {
        JFXPlatform.runLater { f() }
    }

    @JvmStatic
    inline fun runLater(runnable: Runnable) {
        JFXPlatform.runLater(runnable)
    }

    @JvmStatic
    val isFxApplicationThread: Boolean
        inline get() = JFXPlatform.isFxApplicationThread()

    inline fun exit() {
        JFXPlatform.exit()
    }

    @JvmStatic
    var isImplicitExit: Boolean
        inline get() = JFXPlatform.isImplicitExit()
        inline set(value) = JFXPlatform.setImplicitExit(value)

    @JvmStatic
    inline fun isSupported(feature: ConditionalFeature): Boolean = JFXPlatform.isSupported(feature)

    @JvmStatic
    val isAccessibilityActive: Boolean
        inline get() = JFXPlatform.isAccessibilityActive()

    @JvmStatic
    val accessibilityActiveProperty
        @JvmName("accessibilityActiveProperty")
        inline get() = JFXPlatform.accessibilityActiveProperty()
}
