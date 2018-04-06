package org.glavo.viewer.util;

import org.glavo.viewer.Settings;

import java.util.Objects;

public final class Mac {
    private Mac() {
    }

    public static final boolean isMac = Objects.equals(System.getProperty("os.name", ""), "Mac OS X");

    private static boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            synchronized (Mac.class) {
                if (!hasInit) {
                    synchronized (Mac.class) {
                        hasInit = true;
                        System.setProperty(
                                "com.apple.mrj.application.apple.menu.about.name",
                                Settings.getData().getTitle());
                        System.setProperty(
                                "apple.awt.application.name",
                                Settings.getData().getTitle());
                    }
                }
            }
        }
    }
}
