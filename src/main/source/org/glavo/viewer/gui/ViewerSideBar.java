package org.glavo.viewer.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class ViewerSideBar extends TabPane {
    public class ResourceManagerTab extends Tab {
        private final ObservableList<TreeView> folders = FXCollections.observableArrayList();
    }

    public class FileStructTab extends Tab {

    }

    @NotNull
    public final Viewer viewer;


    public ViewerSideBar(@NotNull Viewer viewer) {
        Objects.requireNonNull(viewer);
        this.viewer = viewer;
        this.getTabs().setAll(new ResourceManagerTab(), new FileStructTab());
    }

    @NotNull
    public ResourceManagerTab getResourceManagerTab() {
        return (ResourceManagerTab) getTabs().get(0);
    }

    @NotNull
    public FileStructTab getFileStructTab() {
        return (FileStructTab) getTabs().get(1);
    }
}
