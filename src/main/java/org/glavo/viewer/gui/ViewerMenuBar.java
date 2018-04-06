package org.glavo.viewer.gui;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.collections.WeakListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.glavo.viewer.util.CssUtils;
import org.glavo.viewer.util.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.glavo.viewer.util.ImageUtils.loadImage;

public final class ViewerMenuBar extends MenuBar {
    public static final ResourceBundle resources = ResourceBundle.getBundle("org.glavo.viewer.gui.Menu");

    public static final Image OpenFileIcon = loadImage("/icons/actions/openFile.png");
    public static final Image OpenFolderIcon = loadImage("/icons/actions/openFolder.png");
    public static final Image BulletIcon = loadImage("/icons/ui/bullet.png");
    public static final Image RefreshIcon = loadImage("/icons/actions/refresh.png");

    public static final KeyCombination OpenFileAccelerator = KeyCombination.keyCombination("Shortcut+O");
    public static final KeyCombination OpenFolderAccelerator = KeyCombination.keyCombination("Shortcut+K");

    public static final KeyCombination NewWindowAccelerator = KeyCombination.keyCombination("Shortcut+N");

    public static final KeyCombination NextWindowAccelerator = KeyCombination.keyCombination("Shortcut+Shift+]");
    public static final KeyCombination PreviousWindowAccelerator = KeyCombination.keyCombination("Shortcut+Shift+[");

    private static final String HOME_URI = "https://github.com/ClassViewer/ClassViewer";

    private static final int WINDOW_MENU_WINDOWS_ITEMS_OFFSET = 6;

    public final class FileMenu extends Menu {
        private final MenuItem openFileItem;
        private final MenuItem openFolderItem;
        private final MenuItem exitItem;

        FileMenu() {
            super(resources.getString("FileMenu.text"));

            openFileItem = new MenuItem(resources.getString("FileMenu.OpenFileItem.text"));
            openFolderItem = new MenuItem(resources.getString("FileMenu.OpenFolderItem.text"));
            exitItem = new MenuItem(resources.getString("FileMenu.ExitItem.text"));

            openFileItem.setGraphic(new ImageView(OpenFileIcon));
            openFolderItem.setGraphic(new ImageView(OpenFolderIcon));

            openFileItem.setAccelerator(OpenFileAccelerator);
            openFolderItem.setAccelerator(OpenFolderAccelerator);

            //TODO: openFileItem.setOnAction(event -> {});
            //TODO: openFolderItem.setOnAction(event -> {});
            exitItem.setOnAction(event -> Platform.exit());

            getItems().setAll(openFileItem, openFolderItem, new SeparatorMenuItem(), exitItem);
        }

        @NotNull
        public MenuItem getOpenFileItem() {
            return openFileItem;
        }

        @NotNull
        public MenuItem getOpenFolderItem() {
            return openFolderItem;
        }

        @NotNull
        public MenuItem getExitItem() {
            return exitItem;
        }
    }

    public final class WindowMenu extends Menu {
        private final MenuItem newWindowItem;
        private final MenuItem closeWindowItem;

        private final MenuItem nextWindowItem;
        private final MenuItem previousWindowItem;

        private final ListChangeListener<Viewer> listener = c -> {
            while (c.next()) {
                if (c.wasPermutated()) {
                    for (int i = c.getFrom(); i < c.getTo(); i++) {
                        getItems().set(
                                i + WINDOW_MENU_WINDOWS_ITEMS_OFFSET,
                                new WindowItem(ViewerMenuBar.this.viewer, Viewer.viewers.get(i)));
                    }
                } else if (c.wasRemoved()) {
                    getItems().remove(
                            c.getFrom() + WINDOW_MENU_WINDOWS_ITEMS_OFFSET,
                            c.getTo() + WINDOW_MENU_WINDOWS_ITEMS_OFFSET);
                } else if (c.wasAdded()) {
                    ArrayList<WindowItem> l = new ArrayList<>();
                    for (Viewer v : c.getAddedSubList()) {
                        l.add(new WindowItem(ViewerMenuBar.this.viewer, v));
                    }
                    System.out.println(l);
                    getItems().addAll(c.getFrom() + WINDOW_MENU_WINDOWS_ITEMS_OFFSET, l);
                }
            }
        };

        WindowMenu() {
            super(resources.getString("WindowMenu.text"));
            newWindowItem = new MenuItem(resources.getString("WindowMenu.NewWindowItem.text"));
            closeWindowItem = new MenuItem(resources.getString("WindowMenu.CloseWindowItem.text"));
            nextWindowItem = new MenuItem(resources.getString("WindowMenu.NextWindowItem.text"));
            previousWindowItem = new MenuItem(resources.getString("WindowMenu.PreviousWindowItem.text"));

            newWindowItem.setAccelerator(NewWindowAccelerator);
            nextWindowItem.setAccelerator(NextWindowAccelerator);
            previousWindowItem.setAccelerator(PreviousWindowAccelerator);

            newWindowItem.setOnAction(event -> new Viewer().start(new Stage()));
            closeWindowItem.setOnAction(event -> viewer.getStage().close());
            nextWindowItem.setOnAction(event -> {
                int idx = Viewer.viewers.indexOf(ViewerMenuBar.this.viewer);
                int size = Viewer.viewers.size();
                if (idx == size - 1) {
                    Viewer.viewers.get(0).getStage().toFront();
                } else {
                    Viewer.viewers.get(idx + 1).getStage().toFront();
                }
            });
            previousWindowItem.setOnAction(event -> {
                int idx = Viewer.viewers.indexOf(ViewerMenuBar.this.viewer);
                int size = Viewer.viewers.size();
                if (idx == 0) {
                    Viewer.viewers.get(size - 1).getStage().toFront();
                } else {
                    Viewer.viewers.get(idx - 1).getStage().toFront();
                }
            });

            getItems().addAll(
                    newWindowItem, closeWindowItem, new SeparatorMenuItem(),
                    nextWindowItem, previousWindowItem, new SeparatorMenuItem()
            );
            for (Viewer v : Viewer.viewers) {
                getItems().add(new WindowItem(viewer, v));
            }
            Viewer.viewers.addListener(new WeakListChangeListener<>(listener));
        }
    }

    static class WindowItem extends MenuItem {
        @NotNull
        public final Viewer viewer;

        WindowItem(@Nullable Viewer selfViewer, @NotNull Viewer viewer) {
            this.viewer = viewer;
            textProperty().bind(Bindings.createStringBinding(
                    () -> this.viewer.getTitle() == null ?
                            resources.getString("WindowMenu.Windows.emptyText") :
                            this.viewer.getTitle(),
                    viewer.titleProperty()
            ));

            if (selfViewer == viewer) {
                setGraphic(new ImageView(BulletIcon));
            } else {
                setOnAction(e -> this.viewer.getStage().toFront());
            }
        }
    }

    public final class HelpMenu extends Menu {
        private final MenuItem aboutItem;

        HelpMenu() {
            super(resources.getString("HelpMenu.text"));
            aboutItem = new MenuItem(resources.getString("HelpMenu.AboutItem.text"));
            aboutItem.setOnAction(event -> showAboutDialog());

            getItems().add(aboutItem);
        }

        private void showAboutDialog() {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            ImageView view = new ImageView(Viewer.logo128());
            view.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Logger.info("Open Home Page");
                ViewerMenuBar.this.viewer.getHostServices().showDocument(HOME_URI);
            });

            BorderPane pane = new BorderPane();
            pane.setCenter(view);
            pane.setOnMouseClicked(event -> stage.close());

            Scene scene = new Scene(pane);
            scene.setFill(Color.TRANSPARENT);

            stage.setScene(scene);
            stage.setTitle("About");
            stage.show();
        }
    }


    @NotNull
    public final Viewer viewer;

    private final FileMenu fileMenu;
    private final WindowMenu windowMenu;
    private final HelpMenu helpMenu;

    public ViewerMenuBar(@NotNull Viewer viewer) {
        this.viewer = viewer;

        fileMenu = new FileMenu();
        windowMenu = new WindowMenu();
        helpMenu = new HelpMenu();
        getMenus().addAll(fileMenu, windowMenu, helpMenu);
    }

    @NotNull
    public FileMenu getFileMenu() {
        return fileMenu;
    }

    @NotNull
    public WindowMenu getWindowMenu() {
        return windowMenu;
    }

    @NotNull
    public HelpMenu getHelpMenu() {
        return helpMenu;
    }
}
