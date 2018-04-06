package org.glavo.viewer.gui;

import javafx.scene.layout.BorderPane;
import org.glavo.viewer.util.CSSKt;
import org.jetbrains.annotations.NotNull;

public final class ViewerPane extends BorderPane {
    @NotNull
    public final Viewer viewer;

    public ViewerPane(@NotNull Viewer viewer) {
        this.viewer = viewer;
        this.getStyleClass().add(CSSKt.UI_CLASS);
        this.setTop(new ViewerTopBar(viewer));
    }


    @NotNull
    public ViewerTopBar getTopBar() {
        return (ViewerTopBar) getTop();
    }
}
