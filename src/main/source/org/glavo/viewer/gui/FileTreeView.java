package org.glavo.viewer.gui;

import javafx.scene.control.TreeView;

import java.nio.file.Path;

public abstract class FileTreeView extends TreeView {
    private Path path;

    protected FileTreeView(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
