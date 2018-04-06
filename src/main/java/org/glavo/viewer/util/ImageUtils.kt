@file:JvmName("ImageUtils")
package org.glavo.viewer.util

import javafx.scene.image.Image
import kotlinfx.scene.image
import org.glavo.viewer.gui.Viewer


fun loadImage(url: String): Image = image(Viewer::class.java.getResource(url).toExternalForm())