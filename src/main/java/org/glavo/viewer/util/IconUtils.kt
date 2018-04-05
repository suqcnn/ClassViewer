package org.glavo.viewer.util

import javafx.scene.image.Image
import kotlinfx.scene.image
import org.glavo.viewer.gui.Viewer


fun loadIcon(url: String): Image = image(Viewer::class.java.getResource(url).toExternalForm())