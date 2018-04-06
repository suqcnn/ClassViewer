package org.glavo.viewer;

import com.google.gson.annotations.SerializedName;
import org.glavo.viewer.util.Mac;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings("ALL")
public final class SettingData {
    private static final double DEFAULT_WIDTH;
    private static final double DEFAULT_HEIGHT;

    static {
        DisplayMode mode = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDisplayMode();

        if (mode.getWidth() >= 2880 && mode.getHeight() >= 1800) {
            DEFAULT_WIDTH = 1920;
            DEFAULT_HEIGHT = 1200;
        } else {
            DEFAULT_WIDTH = 1050;
            DEFAULT_HEIGHT = 700;
        }
    }

    @SerializedName("logger.color")
    private boolean colorLog = false;

    @SerializedName("logger.debug")
    private boolean debugLog = false;

    @SerializedName("ui.useSystemTitleBar")
    private boolean useSystemTitleBar = true;

    @SerializedName("ui.useSystemMenuBar")
    private boolean useSystemMenuBar = true;

    @SerializedName("ui.width")
    private double width = DEFAULT_WIDTH;

    @SerializedName("ui.height")
    private double height = DEFAULT_HEIGHT;

    @SerializedName("ui.title")
    private String title = "ClassViewer";

    @SerializedName("ui.skin")
    private String skin = "MODENA";

    public boolean isColorLog() {
        return colorLog;
    }

    public boolean isDebugLog() {
        return debugLog;
    }

    public boolean isUseSystemTitleBar() {
        return useSystemTitleBar;
    }

    public boolean isUseSystemMenuBar() {
        return useSystemMenuBar;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public String getSkin() {
        return skin;
    }
}
