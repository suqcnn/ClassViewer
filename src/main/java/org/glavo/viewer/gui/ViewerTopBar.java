package org.glavo.viewer.gui;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import org.glavo.viewer.Settings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ViewerTopBar extends VBox {
    @NotNull
    public final Viewer viewer;

    @Nullable
    private final ViewerTitleBar titleBar;

    @NotNull
    private final ViewerMenuBar menuBar;

    public ViewerTopBar(@NotNull Viewer viewer) {
        this.viewer = viewer;
        if (Settings.getData().getUseSystemTitleBar()) {
            titleBar = null;
        } else {
            titleBar = new ViewerTitleBar(viewer);
        }

        this.menuBar = new ViewerMenuBar(viewer);

        if (Settings.getData().getUseSystemMenuBar()) {
            Platform.runLater(() -> menuBar.setUseSystemMenuBar(true));
        }
        if (titleBar != null) {
            getChildren().add(titleBar);
        }
        getChildren().add(menuBar);
    }

    @Nullable
    public ViewerTitleBar getTitleBar() {
        return titleBar;
    }

    @NotNull
    public ViewerMenuBar getMenuBar() {
        return menuBar;
    }
}
