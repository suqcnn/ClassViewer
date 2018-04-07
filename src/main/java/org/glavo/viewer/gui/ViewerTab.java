package org.glavo.viewer.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

public class ViewerTab extends Tab {
    public static ViewerTab creatTab(@NotNull URI uri) {
        ViewerTab tab = new ViewerTab();
        tab.setUri(uri);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxWidth(75);
        progressIndicator.setMaxHeight(75);

        tab.setContent(new BorderPane(progressIndicator));
        return tab;
    }

    private URI uri;

    public ViewerTab() {
    }

    public ViewerTab(@Nullable String text) {
        super(text);
    }

    public ViewerTab(@Nullable String text, @Nullable Node content) {
        super(text, content);
    }

    private BooleanProperty refreshable = null;

    @NotNull
    public final BooleanProperty refreshableProperty() {
        if (refreshable == null) {
            refreshable = new SimpleBooleanProperty(false);
        }
        return refreshable;
    }

    public final boolean isRefreshable() {
        return refreshable != null && refreshableProperty().get();
    }

    public final void setRefreshable(boolean v) {
        refreshableProperty().set(v);
    }

    protected BooleanProperty searchable = null;

    public ReadOnlyBooleanProperty searchableProperty() {
        if (searchable == null) {
            searchable = new SimpleBooleanProperty(false);
        }
        return searchable;
    }

    public boolean isSearchable() {
        return searchable != null && searchable.get();
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
