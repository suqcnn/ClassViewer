package org.glavo.viewer.util;

public final class CssUtils {
    private CssUtils() {
    }

    public static final String UI_CLASS = "viewer-ui";
    public static final String TEXT_CLASS = "viewer-text";

    public static String defaultCss() {
        return "." + TEXT_CLASS + " {\n    -fx-font-family: \"" + FontUtils.DEFAULT_TEXT_FONT_FAMILY + "\";\n    -fx-font-size: 15;\n}\n\n." + UI_CLASS + " {\n    -fx-font-family: \"" + FontUtils.DEFAULT_UI_FONT_FAMILY + "\";\n    -fx-font-size: 14;\n}\n\n.root {\n    -fx-background-radius: 20;\n}\n\n.button {\n    -fx-background-color: transparent;\n    -fx-padding: 0.333333em 0.666667em 0.333333em 0.666667em; /* 4 8 4 8 */\n}\n\n.button:hover {\n    -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n    -fx-background-insets: 0 0 -1 0, 0, 1, 2;\n    -fx-background-radius: 3px, 3px, 2px, 1px;\n    -fx-padding: 0.333333em 0.666667em 0.333333em 0.666667em; /* 4 8 4 8 */\n    -fx-text-fill: -fx-text-base-color;\n    -fx-alignment: CENTER;\n    -fx-content-display: LEFT;\n}\n\n.combo-box {\n    -fx-background-color: transparent;\n}\n\n.combo-box:hover {\n    -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n    -fx-text-fill: -fx-text-base-color;\n    -fx-alignment: CENTER;\n    -fx-content-display: LEFT;\n}\n\n.combo-box .arrow, .combo-box .arrow-button{\n    -fx-background-color: transparent;\n}"
                ;
    }
}
