package org.glavo.viewer

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import javafx.collections.ObservableList
import kala.nio.file.bufferedReader
import kala.nio.file.bufferedWriter
import kala.nio.file.div
import kala.nio.file.exists
import kotlinfx.observable
import org.glavo.viewer.util.JsonUtils.gson
import org.glavo.viewer.util.Logger
import org.glavo.viewer.util.ShutdownHook
import java.lang.reflect.Type
import java.net.URI
import java.util.*

data class RecentFile(val type: FileType, val uri: URI)

object RecentFileSerializer : JsonSerializer<RecentFile> {
    override fun serialize(src: RecentFile, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonObject().apply {
            addProperty("type", src.type.toString())
            addProperty("uri", src.uri.toString())
        }

    }

}

object RecentFileDeserializer : JsonDeserializer<RecentFile> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): RecentFile {
        return json.asJsonObject.let {
            RecentFile(FileType.valueOf(it["type"].asString), URI(it["uri"].asString))
        }
    }
}

object RecentFiles {
    val path = (Settings.getSettingsPath() / "recentfiles.json").toAbsolutePath()
    private val tpe = (object : TypeToken<List<RecentFile>>() {}).type
    val recentfiles: ObservableList<RecentFile> = LinkedList<RecentFile>().observable().apply {
        if (path.exists()) {
            try {
                Logger.info("Load recent files from $path")
                path.bufferedReader().use {
                    this.addAll(gson.fromJson<List<RecentFile>>(it, tpe))
                }
            } catch (e: Exception) {
                Logger.warning("", e)
            }
        }

        ShutdownHook.runLater {
            path.bufferedWriter().use {
                gson.toJson(this, tpe, it)
            }
        }
    }


    operator fun plusAssign(file: RecentFile) {
        synchronized(recentfiles) {
            recentfiles.add(0, file)
            val size = recentfiles.size
            if (size > 25)
                recentfiles.remove(25, size)
        }
    }


}