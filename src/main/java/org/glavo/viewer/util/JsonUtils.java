package org.glavo.viewer.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glavo.viewer.RecentFile;

public final class JsonUtils {
    private JsonUtils() {
    }

    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .setLenient()
            .registerTypeAdapter(RecentFile.class, RecentFile.serializer)
            .registerTypeAdapter(RecentFile.class, RecentFile.deserializer)
            .create();
}
