package org.glavo.viewer.util;

import org.glavo.viewer.Settings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

public final class Logger {
    private Logger() {
    }

    private static final boolean debug = Settings.isDebugLog();
    private static final boolean color = Settings.isColorLog();

    static class Exit extends RuntimeException {
        @Override
        public Throwable fillInStackTrace() {
            return this;
        }
    }

    private static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    @SuppressWarnings({"InfiniteLoopStatement", "ConstantConditions"})
    private static Thread loggerThread = new Thread(() -> {
        try {
            Objects.requireNonNull(queue);
            while (true) {
                queue.take().run();
            }
        } catch (Exit ignore) {
        } catch (Throwable e) {
            e.printStackTrace();
        }
    });

    @NotNull
    public static Thread getLoggerThread() {
        return loggerThread;
    }

    public static void runLater(@NotNull Runnable r) {
        queue.add(Objects.requireNonNull(r));
    }

    public static void start() {
        loggerThread.start();
    }

    public static void exit() {
        queue.add(() -> {
            throw new Exit();
        });
    }

    static {
        loggerThread.setDaemon(true);
    }

    public static void trace(@Nullable Object message) {
        trace(message, null);
    }

    public static void trace(@Nullable Object message, @Nullable Throwable exception) {
        runLater(() -> {
            if (color)
                System.out.print("\u001b[36m\u001b[1m[TRACE]\u001b[0m ");
            else
                System.out.print("[TRACE] ");
            System.out.println(message);
        });
    }

    public static void debug(@Nullable Object message) {
        debug(message, null);
    }

    public static void debug(@Nullable Object message, @Nullable Throwable exception) {
        if (!debug) return;
        runLater(() -> {
            if (color)
                System.out.print("\u001b[34m\u001b[1m[DEBUG]\u001b[0m ");
            else
                System.out.print("[DEBUG] ");
            System.out.println(message);
        });
    }


    public static void info(@Nullable Object message) {
        info(message, null);
    }

    public static void info(@Nullable Object message, @Nullable Throwable exception) {
        runLater(() -> {
            if (color)
                System.out.print("\u001b[32m\u001b[1m[INFO]\u001b[0m ");
            else
                System.out.print("[INFO] ");
            System.out.println(message);
        });
    }

    public static void warning(@Nullable Object message) {
        warning(message, null);
    }

    public static void warning(@Nullable Object message, @Nullable Throwable exception) {
        runLater(() -> {
            if (color)
                System.out.print("\u001b[33m\u001b[1m[WARNING]\u001b[0m ");
            else
                System.out.print("[WARNING] ");
            System.out.println(message);
        });
    }

    public static void error(@Nullable Object message) {
        error(message, null);
    }

    public static void error(@Nullable Object message, @Nullable Throwable exception) {
        runLater(() -> {
            if (color)
                System.err.println("\u001b[31m\u001b[1m[ERROR]\u001b[0m " + message);
            else
                System.err.println("[ERROR] " + message);
            if (exception != null)
                exception.printStackTrace(System.err);
        });
    }
}
