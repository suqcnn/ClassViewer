package kotlinfx

typealias PopupWindow = javafx.stage.PopupWindow

typealias Popup = javafx.stage.Popup

inline fun popup(initializer: Popup.() -> Unit = {}): Popup {
    return Popup().apply(initializer)
}