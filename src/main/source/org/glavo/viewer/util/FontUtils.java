package org.glavo.viewer.util;

import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FontUtils {
    private FontUtils() {
    }

    public static final String DEFAULT_UI_FONT_FAMILY;
    public static final String DEFAULT_TEXT_FONT_FAMILY;

    static {
        List<String> fonts = Font.getFamilies();
        List<String> uiFontFamilies = Arrays.asList(
                "PingFang SC",
                "Microsoft YaHei UI",
                "Ubuntu",
                "Segoe UI"
        );
        List<String> textFontFamilies = Arrays.asList(
                "Consolas",
                "Source Code Pro",
                "Fira Code",
                "DejaVu Sans Mono",
                "Monospaced"
        );

        String uiFontFamily = null;
        String textFontFamily = null;

        for (String family : uiFontFamilies) {
            if (fonts.contains(family)) {
                uiFontFamily = family;
                break;
            }
        }
        DEFAULT_UI_FONT_FAMILY = uiFontFamily != null ? uiFontFamily : "Dialog";

        for (String family : textFontFamilies) {
            if (fonts.contains(family)) {
                textFontFamily = family;
                break;
            }
        }
        DEFAULT_TEXT_FONT_FAMILY = uiFontFamily != null ? textFontFamily : "Monospaced";
    }
}
