package org.glavo.viewer.gui;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.BorderPane;
import org.glavo.viewer.util.CssUtils;
import org.jetbrains.annotations.NotNull;

public final class ViewerPane extends BorderPane {
    @NotNull
    public final Viewer viewer;

    public ViewerPane(@NotNull Viewer viewer) {
        this.viewer = viewer;
        this.getStyleClass().add(CssUtils.UI_CLASS);
        this.setTop(new ViewerTopBar(viewer));
        this.setCenter(new ViewerTabPane(viewer));

        getTopBar().getToolBar().getRefreshButton().disableProperty().bind(getTabPane().refreshableProperty().not());
    }

    @NotNull
    public ViewerTopBar getTopBar() {
        return (ViewerTopBar) getTop();
    }

    @NotNull
    public ViewerTabPane getTabPane() {
        return (ViewerTabPane) getCenter();
    }
}
