@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias Image = javafx.scene.image.Image

fun Image.view(): ImageView = ImageView(this)