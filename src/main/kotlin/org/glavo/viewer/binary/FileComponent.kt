package org.glavo.viewer.binary

import javafx.scene.control.TreeItem

@Suppress("UNCHECKED_CAST")
abstract class FileComponent<Component : FileComponent<Component, Reader>, Reader : FileReader>
    : TreeItem<Component>() {
    var name: String? = null
    var desc: String? = null
    var offset: Int = -1
    var length: Int = -1

    init {
        @Suppress("LeakingThis")
        super.setValue(this as Component)
    }

    open val components: MutableList<Component?>
        get() = super.getChildren() as MutableList<Component?>

    open fun readContent(reader: Reader) {
        components.forEach { it!!.readContent(reader) }
    }

    open fun postRead(reader: Reader) {

    }

    operator fun get(name: String): Component? {
        return components.find { it?.name == name }
    }

    fun add(name: String?, component: Component?) {
        if (name != null)
            component?.name = name
        components.add(component)
    }

    fun add(component: Component?) {
        components.add(component)
    }

    operator fun plusAssign(component: Component?) {
        components += component
    }

    override fun isLeaf(): Boolean {
        return components.isNotEmpty()
    }

    override fun toString(): String {
        return when {
            name != null && desc != null ->
                "$name: $desc"
            name != null ->
                desc!!
            desc != null ->
                name!!
            else ->
                this::class.java.simpleName
        }
    }

}