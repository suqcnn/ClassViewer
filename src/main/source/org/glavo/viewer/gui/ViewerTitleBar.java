package org.glavo.viewer.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.glavo.viewer.util.CssUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;

import static org.glavo.viewer.util.ImageUtils.loadImage;

@SuppressWarnings("FieldCanBeLocal")
public final class ViewerTitleBar extends ToolBar {
    public static final ResourceBundle resources = ResourceBundle.getBundle("org.glavo.viewer.gui.TitleBar");

    public static final Image CloseActiveIcon = loadImage("/icons/ui/closeActive.png");
    public static final Image MaximizeIcon = loadImage("/icons/ui/maximize.png");
    public static final Image MinimizeIcon = loadImage("/icons/ui/minimize.png");
    public static final Image RestoreIcon = loadImage("/icons/ui/restore.png");

    private static final int MAX_AND_RESTORE_BUTTON_COUNT = 5;

    @NotNull
    public final Viewer viewer;

    private final ImageView logoView;
    private final Label titleLabel;

    private final Button closeActiveButton;
    private final Button maximizeButton;
    private final Button minimizeButton;
    private final Button restoreButton;

    private double xOffset = 0;
    private double yOffset = 0;


    public ViewerTitleBar(@NotNull Viewer viewer) {
        this.viewer = viewer;
        Stage stage = viewer.getStage();

        logoView = new ImageView(Viewer.logo24());
        Text noop1 = new Text();


        titleLabel = new Label();
        HBox noop2 = new HBox();

        closeActiveButton = new Button();
        maximizeButton = new Button();
        minimizeButton = new Button();
        restoreButton = new Button();

        titleLabel.textProperty().bind(stage.titleProperty());

        closeActiveButton.setGraphic(new ImageView(CloseActiveIcon));
        maximizeButton.setGraphic(new ImageView(MaximizeIcon));
        minimizeButton.setGraphic(new ImageView(MinimizeIcon));
        restoreButton.setGraphic(new ImageView(RestoreIcon));

        closeActiveButton.setTooltip(new Tooltip(resources.getString("CloseActiveButton.tooltip")));
        maximizeButton.setTooltip(new Tooltip(resources.getString("MaximizeButton.tooltip")));
        minimizeButton.setTooltip(new Tooltip(resources.getString("MinimizeButton.tooltip")));
        restoreButton.setTooltip(new Tooltip(resources.getString("RestoreButton.tooltip")));

        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.getItems().set(MAX_AND_RESTORE_BUTTON_COUNT, restoreButton);
            } else {
                this.getItems().set(MAX_AND_RESTORE_BUTTON_COUNT, maximizeButton);
            }
        });

        minimizeButton.setOnAction(event -> this.viewer.getStage().setIconified(true));
        maximizeButton.setOnAction(event -> this.viewer.getStage().setMaximized(true));
        restoreButton.setOnAction(event -> this.viewer.getStage().setMaximized(false));
        closeActiveButton.setOnAction(event -> this.viewer.getStage().close());

        this.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                this.viewer.getStage().setMaximized(!this.viewer.getStage().isMaximized());
            }
        });

        this.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        this.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Stage s = viewer.getStage();
                s.setX(event.getSceneX() - xOffset);
                s.setY(event.getSceneY() - yOffset);
            }
        });

        HBox.setHgrow(noop1, Priority.NEVER);
        HBox.setHgrow(noop2, Priority.ALWAYS);

        HBox.setHgrow(logoView, Priority.NEVER);
        HBox.setHgrow(minimizeButton, Priority.NEVER);
        HBox.setHgrow(maximizeButton, Priority.NEVER);
        HBox.setHgrow(restoreButton, Priority.NEVER);
        HBox.setHgrow(closeActiveButton, Priority.NEVER);

        if (stage.isMaximized()) {
            this.getItems().setAll(logoView, noop1, titleLabel, noop2, minimizeButton, restoreButton, closeActiveButton);
        } else {
            this.getItems().setAll(logoView, noop1, titleLabel, noop2, minimizeButton, maximizeButton, closeActiveButton);
        }
    }
}
