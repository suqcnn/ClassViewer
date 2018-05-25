package org.glavo.viewer.gui;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import org.glavo.viewer.Settings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ViewerTopBar extends VBox {
    @NotNull
    public final Viewer viewer;

    private final ViewerTitleBar titleBar;

    private final ViewerMenuBar menuBar;

    private final ViewerToolBar toolBar;

    public ViewerTopBar(@NotNull Viewer viewer) {
        this.viewer = viewer;
        if (Settings.getData().isUseSystemTitleBar()) {
            titleBar = null;
        } else {
            titleBar = new ViewerTitleBar(viewer);
        }

        menuBar = new ViewerMenuBar(viewer);
        toolBar = new ViewerToolBar(viewer);


        if (Settings.getData().isUseSystemMenuBar()) {
            Platform.runLater(() -> menuBar.setUseSystemMenuBar(true));
        }
        if (titleBar != null) {
            getChildren().add(titleBar);
        }
        getChildren().add(menuBar);
        getChildren().add(toolBar);
    }

    @Nullable
    public ViewerTitleBar getTitleBar() {
        return titleBar;
    }

    @NotNull
    public ViewerMenuBar getMenuBar() {
        return menuBar;
    }

    @NotNull
    public ViewerToolBar getToolBar() {
        return toolBar;
    }
}
