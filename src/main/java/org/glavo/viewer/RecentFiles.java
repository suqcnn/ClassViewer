package org.glavo.viewer;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.glavo.viewer.util.JsonUtils;
import org.glavo.viewer.util.ShutdownHook;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class RecentFiles {
    private RecentFiles() {
    }

    public static final Path recentFilesPath = Settings.getSettingsPath().resolve("recentfiles.json");

    private static final ObservableList<RecentFile> recentFiles =
            FXCollections.observableList(new LinkedList<>());

    private static final Type tpe = new TypeToken<List<RecentFile>>() {
    }.getType();

    public static synchronized void addListener(@NotNull ListChangeListener<? super RecentFile> listener) {
        Objects.requireNonNull(listener);
        recentFiles.addListener(listener);
    }

    public static synchronized void addRecentFile(@NotNull RecentFile file) {
        Objects.requireNonNull(file);
        int size = recentFiles.size();
        if (size > 25) {
            recentFiles.remove(25, size - 1);
        }
        recentFiles.add(0, file);
    }

    public static RecentFile get(int index) {
        return recentFiles.get(index);
    }

    @NotNull
    public static ObservableList<RecentFile> getRecentFiles() {
        return recentFiles;
    }

    static {
        ShutdownHook.runLater(() -> {
            try (Writer writer = Files.newBufferedWriter(recentFilesPath)) {
                JsonUtils.gson.toJson(recentFiles, tpe, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
