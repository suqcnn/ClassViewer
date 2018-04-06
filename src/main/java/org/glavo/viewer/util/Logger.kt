package org.glavo.viewer.util

import org.glavo.viewer.Settings
import java.util.concurrent.LinkedBlockingQueue

object Logger {
    @JvmStatic
    var color: Boolean
        inline get() = Settings.data.colorLog
        inline set(value) {
            Settings.data.colorLog = value
        }
    @JvmStatic
    var debug: Boolean
        inline get() = Settings.data.debugLog
        inline set(value) {
            Settings.data.debugLog = value
        }

    @JvmStatic
    fun setting(name: String, value: Any?) {
        LoggerUtils.runLater {
            if (color)
                println("\u001B[35m\u001B[1m[Setting]\u001B[0m \u001b[34m\u001b[1m$name\u001b[0m=\u001b[1m$value\u001b[0m")
            else
                println("[Setting] $name=$value")

        }
    }

    @JvmStatic
    @JvmOverloads
    fun trace(message: Any? = "", exception: Throwable? = null) {
        LoggerUtils.runLater {
            if (color)
                println("\u001b[36m\u001b[1m[TRACE]\u001b[0m $message")
            else
                println("[TRACE] $message")
            exception?.printStackTrace(System.out)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun debug(message: Any? = "", exception: Throwable? = null) {
        LoggerUtils.runLater {
            if (color)
                println("\u001b[34m\u001b[1m[DEBUG]\u001b[0m $message")
            else
                println("[DEBUG] $message")
            exception?.printStackTrace(System.out)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun info(message: Any? = "", exception: Throwable? = null) {
        LoggerUtils.runLater {
            if (color)
                println("\u001b[32m\u001b[1m[INFO]\u001b[0m $message")
            else
                println("[INFO] $message")
            exception?.printStackTrace(System.out)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun warning(message: Any? = "", exception: Throwable? = null) {
        LoggerUtils.runLater {
            if (color)
                println("\u001b[33m\u001b[1m[WARNING]\u001b[0m $message")
            else
                println("[WARNING] $message")
            exception?.printStackTrace(System.out)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun error(message: Any? = "", exception: Throwable? = null) {
        LoggerUtils.runLater {
            if (color)
                System.err.println("\u001b[31m\u001b[1m[ERROR]\u001b[0m $message")
            else
                System.err.println("[ERROR] $message")
            exception?.printStackTrace(System.err)

        }
    }
}

object LoggerUtils : Thread() {
    private class ExitException : Throwable() {
        override fun fillInStackTrace(): Throwable = this
    }

    private val queue: LinkedBlockingQueue<() -> Unit> = LinkedBlockingQueue()


    init {
        this.isDaemon = true
    }

    override fun run() {
        try {
            while (true) {
                queue.poll()?.invoke()
            }
        } catch (_: ExitException) {
        } catch (e: Exception) {
            e.printStackTrace()
            System.exit(1)
        }
    }

    fun exit() {
        queue += { throw ExitException() }
    }

    fun runLater(f: () -> Unit) {
        queue += f
    }
}
