@file:JvmName("Swing")

package kotlinfx

import javax.swing.JComponent
import javax.swing.SwingUtilities

typealias SwingNode = javafx.embed.swing.SwingNode


fun JComponent.asJFXNode(): SwingNode {
    val node = SwingNode()
    SwingUtilities.invokeLater { node.content = this }
    return node
}