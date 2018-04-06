package org.glavo.viewer;

import org.glavo.viewer.util.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings {
    private Settings() {
    }

    private static final Path settingsPath;
    private static final Path settingsDataPath;
    private static final SettingData data;
    private static final String cssURL;

    static {
        String p = System.getProperty("viewer.settings.path");
        if (p != null) {
            settingsPath = Paths.get(p).toAbsolutePath();
        } else {
            settingsPath = Paths.get(System.getProperty("user.home"), ".viewer").toAbsolutePath();
        }
        if (Files.notExists(settingsPath)) {
            try {
                Files.createDirectories(settingsPath);
            } catch (IOException e) {
                Logger.warning("", e);
            }
        }

        settingsDataPath = settingsPath.resolve("settings.json").toAbsolutePath();
        if (Files.notExists(settingsDataPath)) {
            data = new SettingData();
            try (Writer writer = Files.newBufferedWriter(settingsDataPath)) {
                JsonUtils.gson.toJson(data, writer);
            } catch (IOException e) {
                Logger.warning("", e);
            }
        } else {
            SettingData d = null;
            try (Reader reader = Files.newBufferedReader(settingsDataPath)) {
                d = JsonUtils.gson.fromJson(reader, SettingData.class);
            } catch (IOException e) {
                Logger.warning("", e);
                if (d == null) {
                    d = new SettingData();
                }
                try (Writer writer = Files.newBufferedWriter(settingsDataPath)) {
                    JsonUtils.gson.toJson(d, writer);
                } catch (IOException ee) {
                    Logger.warning("", ee);
                }
            } finally {
                data = d;
            }
        }

        Logger.info(data);
        Path css = settingsPath.resolve("viewer.css");
        if (Files.notExists(css)) {
            try {
                Files.write(css, CssUtils.defaultCss().getBytes("UTF-8"));
            } catch (IOException e) {
                Logger.warning("", e);
            }
        }

        cssURL = UrlUtils.pathToURL(css).toExternalForm();
        Logger.start();
        ShutdownHook.init();
        if (Mac.isMac) {
            Mac.init();
        }
    }

    @NotNull
    public static Path getSettingsPath() {
        return settingsPath;
    }

    @NotNull
    public static Path getSettingsDataPath() {
        return settingsDataPath;
    }

    @NotNull
    public static SettingData getData() {
        return data;
    }

    @NotNull
    public static String getCssURL() {
        return cssURL;
    }

    public static boolean isColorLog() {
        return data.isColorLog();
    }

    public static boolean isDebugLog() {
        return data.isDebugLog();
    }

    public static boolean isUseSystemTitleBar() {
        return data.isUseSystemTitleBar();
    }

    public static boolean isUseSystemMenuBar() {
        return data.isUseSystemMenuBar();
    }

    public static double getWidth() {
        return data.getWidth();
    }

    public static double getHeight() {
        return data.getHeight();
    }

    @NotNull
    public static String getTitle() {
        return data.getTitle();
    }

    @NotNull
    public static String getSkin() {
        return data.getSkin();
    }
}
