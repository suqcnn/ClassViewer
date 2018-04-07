package org.glavo.viewer.binary;

import org.glavo.viewer.FileType;
import org.glavo.viewer.gui.Viewer;
import org.glavo.viewer.util.PathUtils;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryFileType extends FileType {
    public static final BinaryFileType Instance = new BinaryFileType("binary");

    @NotNull
    public static BinaryFileType getInstance() {
        return Instance;
    }

    private final String name;

    public BinaryFileType(String name) {
        this.name = name;
    }

    @Override
    public boolean test(@NotNull URI uri) {
        Path path = PathUtils.toPath(uri);
        return path != null && Files.exists(path);
    }

    @Override
    public void open(@NotNull Viewer viewer, @NotNull URI uri) throws Exception {

    }

    @Override
    public String toString() {
        return name;
    }
}
