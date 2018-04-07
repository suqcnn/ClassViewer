package org.glavo.viewer.binary;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import org.glavo.viewer.util.CssUtils;
import org.glavo.viewer.util.HexText;
import org.jetbrains.annotations.NotNull;

public class BinaryPane extends ScrollPane {
    private final TextArea textArea1;
    private final TextArea textArea2;
    private final TextArea textArea3;

    public BinaryPane(@NotNull HexText text) {
        getStyleClass().add(CssUtils.TEXT_CLASS);

        textArea1 = new TextArea(text.getRowHeaderText());
        textArea2 = new TextArea(text.getBytesText());
        textArea3 = new TextArea(text.getAsciiString());

        textArea1.setPrefColumnCount(6);
        textArea2.setPrefColumnCount(46);
        textArea3.setPrefColumnCount(16);

        int rowCount = text.getRowHeaderText().length() / 9 + 2;
        textArea1.setPrefRowCount(rowCount);
        textArea2.setPrefRowCount(rowCount);
        textArea3.setPrefRowCount(rowCount);

        textArea1.setEditable(false);
        textArea2.setEditable(false);
        textArea3.setEditable(false);

        HBox box = new HBox();
        box.getChildren().addAll(textArea1, textArea2, textArea3);
    }

    public void selectRange(int start, int end) {
        assert start < end && start >= 0;
        textArea1.selectRange(start * 3, end * 3 - 1);
        textArea2.selectRange(
                (start / HexText.BYTES_PER_ROW) * (HexText.BYTES_PER_ROW + 1) + (start % HexText.BYTES_PER_ROW),
                (end / HexText.BYTES_PER_ROW) * (HexText.BYTES_PER_ROW + 1) + (start % HexText.BYTES_PER_ROW)
        );
    }

    @NotNull
    public TextArea getRowHeaderText() {
        return textArea1;
    }

    @NotNull
    public TextArea getBytesText() {
        return textArea2;
    }

    @NotNull
    public TextArea getAsciiText() {
        return textArea3;
    }
}
