package org.glavo.viewer.gui;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;

import static org.glavo.viewer.util.ImageUtils.loadImage;

public final class ViewerToolBar extends ToolBar {
    public static final ResourceBundle resources = ResourceBundle.getBundle("org.glavo.viewer.gui.ToolBar");

    public static final Image RefreshIcon = loadImage("/icons/actions/refresh.png");

    @NotNull
    public final Viewer viewer;

    private final Button openFileButton;
    private final Button openFolderButton;

    private final Button refreshButton;

    public ViewerToolBar(@NotNull Viewer viewer) {
        this.viewer = viewer;
        openFileButton = new Button();
        openFolderButton = new Button();
        refreshButton = new Button();

        openFileButton.setGraphic(new ImageView(ViewerMenuBar.OpenFileIcon));
        openFolderButton.setGraphic(new ImageView(ViewerMenuBar.OpenFolderIcon));
        refreshButton.setGraphic(new ImageView(RefreshIcon));

        openFileButton.setTooltip(new Tooltip(resources.getString("OpenFileButton.tooltip")));
        openFolderButton.setTooltip(new Tooltip(resources.getString("OpenFolderButton.tooltip")));
        refreshButton.setTooltip(new Tooltip(resources.getString("RefreshButton.tooltip")));

        getItems().addAll(
                openFileButton, openFolderButton, new Separator(Orientation.VERTICAL),
                refreshButton
        );
    }

    @NotNull
    public Button getOpenFileButton() {
        return openFileButton;
    }

    @NotNull
    public Button getOpenFolderButton() {
        return openFolderButton;
    }

    @NotNull
    public Button getRefreshButton() {
        return refreshButton;
    }
}
