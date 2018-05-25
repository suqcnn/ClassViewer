package org.glavo.viewer.gui;

import org.glavo.viewer.util.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Skin {
    private Skin() {

    }

    @NotNull
    public static final String DEFAULT_SKIN = "MODENA";

    private static final Map<String, String> skins = new LinkedHashMap<>();

    static {
        skins.put("modena", "MODENA");
        skins.put("caspian", "CASPIAN");
    }

    @NotNull
    public static String skinOf(@Nullable String name) {
        if (name == null) {
            Logger.warning("Skin name is null");
            return DEFAULT_SKIN;
        }

        name = name.trim().toLowerCase();
        for (Map.Entry<String, String> entry : skins.entrySet()) {
            if (entry.getKey().equals(name)) {
                return entry.getValue();
            }
        }
        return DEFAULT_SKIN;
    }
}
