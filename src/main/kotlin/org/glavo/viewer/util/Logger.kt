package org.glavo.viewer.util

class Logger(var colored: Boolean = true) {

    fun setting(name: String, value: Any?) {
        if (colored) {
            println("\u001B[35m\u001B[1m[Setting]\u001B[0m \u001b[34m\u001b[1m$name\u001b[0m=\u001b[1m$value\u001b[0m")
        } else {
            println("[Setting] $name=$value")
        }
    }

    fun trace(obj: Any?) {
        if (colored)
            print("\u001b[36m\u001b[1m[TRACE]\u001b[0m ")
        else
            print("[TRACE] ")
        println(obj)
    }

    fun debug(obj: Any) {
        //if (!debug) return
        if (colored)
            print("\u001b[34m\u001b[1m[DEBUG]\u001b[0m ")
        else
            print("[DEBUG] ")
        println(obj)
    }

    fun info(message: Any?) {
        if (colored)
            print("\u001b[32m\u001b[1m[INFO]\u001b[0m ")
        else
            print("[INFO] ")
        println(message)
    }

    fun warning(message: Any?) {
        if (colored)
            print("\u001b[33m\u001b[1m[WARNING]\u001b[0m ")
        else
            print("[WARNING] ")
        println(message)
    }

    fun error(message: Any?) {
        if (colored)
            print("\u001b[31m\u001b[1m[ERROR]\u001b[0m ")
        else
            print("[ERROR] ")
        System.err.println(message)
    }


    fun error(exception: Throwable?) {
        error(exception?.message ?: "", exception)
    }


    fun error(message: Any?, exception: Throwable?) {
        if (colored)
            println("\u001b[31m\u001b[1m[ERROR]\u001b[0m $message")
        else
            println("[ERROR] $message")

        exception?.printStackTrace(System.err) ?: println(null)
    }
}