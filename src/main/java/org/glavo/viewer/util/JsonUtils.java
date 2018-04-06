package org.glavo.viewer.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glavo.viewer.RecentFile;
import org.glavo.viewer.RecentFileDeserializer;
import org.glavo.viewer.RecentFileSerializer;

public final class JsonUtils {
    private JsonUtils() {
    }

    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .setLenient()
            .registerTypeAdapter(RecentFile.class, RecentFileSerializer.INSTANCE)
            .registerTypeAdapter(RecentFile.class, RecentFileDeserializer.INSTANCE)
            .create();
}
