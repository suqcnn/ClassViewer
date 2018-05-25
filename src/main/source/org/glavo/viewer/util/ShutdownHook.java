package org.glavo.viewer.util;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class ShutdownHook extends Thread {
    private static final ShutdownHook INSTANCE = new ShutdownHook();

    public static void init() {
        // noop
    }

    public static void runLater(@NotNull Runnable r) {
        Objects.requireNonNull(r);
        INSTANCE.l.add(r);
    }

    private List<Runnable> l = Collections.synchronizedList(new LinkedList<>());

    private ShutdownHook() {
        Runtime.getRuntime().addShutdownHook(this);
    }

    @Override
    public void run() {
        for (Runnable r : l) {
            try {
                r.run();
            } catch (Exception e) {
                Logger.warning("", e);
            }
        }
        Logger.info("Exit");
        Logger.exit();
        try {
            Logger.getLoggerThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
