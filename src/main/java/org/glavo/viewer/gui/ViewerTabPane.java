package org.glavo.viewer.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ViewerTabPane extends TabPane {
    @NotNull
    public final Viewer viewer;

    public ViewerTabPane(@NotNull Viewer viewer) {
        this.viewer = viewer;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public ObservableList<ViewerTab> getViewerTabs() {
        return (ObservableList) getTabs();
    }

    @Nullable
    public ViewerTab getTab() {
        return (ViewerTab) getSelectionModel().getSelectedItem();
    }

    private BooleanProperty refreshable = null;

    @NotNull
    public final BooleanProperty refreshableProperty() {
        if (refreshable == null) {
            refreshable = new SimpleBooleanProperty(false);
            selectionModelProperty().addListener((observable, oldValue, newValue) -> {
                ViewerTab tab = (ViewerTab) newValue.getSelectedItem();
                refreshable.unbind();
                if (tab != null) {
                    refreshable.bind(tab.refreshableProperty());
                } else {
                    refreshable.set(false);
                }
            });
        }
        return refreshable;
    }

    public final boolean isRefreshable() {
        return refreshable != null && refreshableProperty().get();
    }

    public final void setRefreshable(boolean v) {
        refreshableProperty().set(v);
    }
}
