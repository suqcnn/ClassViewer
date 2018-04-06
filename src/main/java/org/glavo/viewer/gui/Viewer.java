package org.glavo.viewer.gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.glavo.viewer.Settings;
import org.glavo.viewer.util.ImageUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public final class Viewer extends Application {
    public static void main(String[] args) {
        String ignore = Settings.getCssURL();
        Application.launch(Viewer.class, args);
    }

    @NotNull
    public static final ObservableList<Viewer> viewers =
            FXCollections.observableArrayList();

    private static Image logo16;
    private static Image logo24;
    private static Image logo32;
    private static Image logo128;

    @NotNull
    public static Image logo16() {
        if (logo16 == null) {
            logo16 = ImageUtils.loadImage("/icons/ui/viewer_16x16.png");
        }
        return logo16;
    }

    @NotNull
    public static Image logo24() {
        if (logo24 == null) {
            logo24 = ImageUtils.loadImage("/icons/ui/viewer_24x24.png");
        }
        return logo24;
    }


    @NotNull
    public static Image logo32() {
        if (logo32 == null) {
            logo32 = ImageUtils.loadImage("/icons/ui/viewer_32x32.png");
        }
        return logo32;
    }

    @NotNull
    public static Image logo128() {
        if (logo128 == null) {
            logo128 = ImageUtils.loadImage("/icons/ui/viewer_128x128.png");
        }
        return logo128;
    }


    private StringProperty titleProperty = new SimpleStringProperty();
    private Stage stage;

    public Viewer() {
        viewers.add(this);
    }

    @Override
    public void start(@NotNull Stage stage) {
        this.stage = stage;
        stage.titleProperty().bind(Bindings.createStringBinding(
                () -> getTitle() == null ?
                        Settings.getData().getTitle()
                        : Settings.getData().getTitle() + " " + this.getTitle(),
                titleProperty()
        ));
        stage.getIcons().addAll(logo16(), logo24(), logo32());
        ViewerPane pane = new ViewerPane(this);

        Scene scene = new Scene(pane, Settings.getData().getWidth(), Settings.getData().getHeight());
        scene.getStylesheets().add(Settings.getCssURL());
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> viewers.remove(this));
        stage.show();
    }

    public void addTab(@NotNull ViewerTab tab) {
        Objects.requireNonNull(tab);
        getTabPane().getTabs().add(getTabPane().getSelectionModel().getSelectedIndex() + 1, tab);
        getTabPane().getSelectionModel().select(tab);
    }

    @NotNull
    public StringProperty titleProperty() {
        return this.titleProperty;
    }

    @Nullable
    public String getTitle() {
        return titleProperty().getValue();
    }

    public void setTitle(@Nullable String title) {
        titleProperty().setValue(title);
    }

    @NotNull
    public Stage getStage() {
        return this.stage;
    }

    @NotNull
    public Scene getScene() {
        return getStage().getScene();
    }

    @NotNull
    public ViewerPane getPane() {
        return (ViewerPane) getScene().getRoot();
    }

    @NotNull
    public ViewerTabPane getTabPane() {
        return getPane().getTabPane();
    }
}
