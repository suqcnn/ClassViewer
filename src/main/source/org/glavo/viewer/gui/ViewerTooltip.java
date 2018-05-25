package org.glavo.viewer.gui;

import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
import javafx.scene.text.TextAlignment;

public class ViewerTooltip extends Tooltip {
    public ViewerTooltip() {
        init();
    }

    public ViewerTooltip(String text) {
        super(text);
        init();
    }

    private void init() {
        setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
        setTextAlignment(TextAlignment.JUSTIFY);
        setWrapText(true);
    }
}
