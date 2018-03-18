@file:JvmName("Alert")

package kotlinfx

typealias Alert = javafx.scene.control.Alert

typealias AlertType = javafx.scene.control.Alert.AlertType

inline fun alert(alertType: AlertType, contextText: String = "", vararg buttons: ButtonType, initializer: Alert.() -> Unit = {}): Alert {
    return Alert(alertType, contextText, *buttons).apply(initializer)
}

@JvmName("alertOfButtonArray")
inline fun alert(alertType: AlertType, contextText: String = "", buttons: Array<out ButtonType>, initializer: Alert.() -> Unit = {}): Alert {
    return Alert(alertType, contextText, *buttons).apply(initializer)
}

inline fun alert(alertType: AlertType, contextText: String = "", buttons: Collection<ButtonType>, initializer: Alert.() -> Unit = {}): Alert {
    return Alert(alertType, contextText, *buttons.toTypedArray()).apply(initializer)
}