package org.glavo.viewer.util;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

public final class ImageUtils {
    private ImageUtils() {
    }

    @NotNull
    public static Image loadImage(@NotNull String url) {
        return new Image(ImageUtils.class.getResource(url).toExternalForm());
    }
}
