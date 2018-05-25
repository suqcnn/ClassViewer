package org.glavo.viewer;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.glavo.viewer.gui.Viewer;
import org.glavo.viewer.gui.ViewerTab;
import org.glavo.viewer.gui.ViewerTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public abstract class FileType implements Predicate<URI> {
    private static final List<FileType> fileTypes;

    static {
        ArrayList<FileType> l = new ArrayList<>();

        for (FileType type : ServiceLoader.load(FileType.class)) {
            l.add(type);
        }
        fileTypes = Collections.unmodifiableList(l);
    }

    @NotNull
    public static List<FileType> getFileTypes() {
        return fileTypes;
    }

    @NotNull
    public static FileType valueOf(@NotNull String name) {
        Objects.requireNonNull(name);
        for (FileType type : getFileTypes()) {
            if (name.equals(type.toString())) {
                return type;
            }
        }
        throw new UnsupportedOperationException(); //TODO
    }

    @Nullable
    private Image icon;

    @Nullable
    private FileChooser.ExtensionFilter filter;

    @Nullable
    public Image getIcon() {
        return icon;
    }

    protected void setIcon(@Nullable Image icon) {
        this.icon = icon;
    }

    @Nullable
    public FileChooser.ExtensionFilter getFilter() {
        return filter;
    }

    protected void setFilter(@Nullable FileChooser.ExtensionFilter filter) {
        this.filter = filter;
    }

    public abstract boolean test(@NotNull URI uri);

    public abstract void open(@NotNull Viewer viewer, @NotNull URI uri) throws Exception;
}
