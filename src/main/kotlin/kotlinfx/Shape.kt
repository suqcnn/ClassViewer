@file:JvmName("KotlinFX")
@file:JvmMultifileClass

package kotlinfx

typealias Shape = javafx.scene.shape.Shape

typealias Shape3D = javafx.scene.shape.Shape3D


typealias Rectangle = javafx.scene.shape.Rectangle

inline fun rectangle(initializer: Rectangle.() -> Unit = {}): Rectangle {
    return Rectangle().apply(initializer)
}

inline fun rectangle(width: Double, height: Double, initializer: Rectangle.() -> Unit = {}): Rectangle {
    return Rectangle(width, height).apply(initializer)
}