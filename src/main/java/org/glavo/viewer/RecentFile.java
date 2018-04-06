package org.glavo.viewer;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;

public final class RecentFile {
    public static final JsonSerializer<RecentFile> serializer =
            (@NotNull RecentFile src, Type typeOfSrc, JsonSerializationContext context) -> {
                JsonObject json = new JsonObject();
                json.addProperty("type", src.type.toString());
                json.addProperty("uri", src.uri.toString());
                return json;
            };

    public static final JsonDeserializer<RecentFile> deserializer =
            (@NotNull JsonElement json, Type typeOfT, JsonDeserializationContext context) -> {
                try {
                    return new RecentFile(FileType.valueOf(
                            json.getAsJsonObject().get("type").getAsString()),
                            new URI(json.getAsJsonObject().get("uri").getAsString()
                            ));
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            };

    private FileType type;
    private URI uri;

    public RecentFile(FileType type, URI uri) {
        this.type = type;
        this.uri = uri;
    }

    @NotNull
    public FileType getType() {
        return type;
    }

    @NotNull
    public URI getUri() {
        return uri;
    }

    @Override
    public int hashCode() {
        return type.hashCode() * 13 + uri.hashCode();
    }
}
