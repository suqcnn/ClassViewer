package org.glavo.viewer.util;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Objects;

public final class HexText {
    public static final int BYTES_PER_ROW = 16;

    public static String byteToString(byte b) {
        return byteStrings[b & 0xFF];
    }

    private static String[] byteStrings = new String[256];

    static {
        for (int i = 0; i < 256; i++) {
            byteStrings[i] = String.format("%02X", i);
        }
    }

    private static int len(ByteBuffer buffer) {
        return buffer.limit() - buffer.position();
    }


    private static String buildRowHeaderText(ByteBuffer data) {
        int size = len(data);
        StringBuilder sb = new StringBuilder((size / BYTES_PER_ROW + 1) * 9);

        for (int i = 0; i < size; i += BYTES_PER_ROW) {
            sb.append(String.format("%08X\n", i));
        }

        return sb.toString();
    }

    private static String buildBytesText(ByteBuffer data) {
        int size = len(data);
        StringBuilder sb = new StringBuilder(size * 3);
        while (data.hasRemaining()) {
            size = Math.min(BYTES_PER_ROW, len(data));

            for (int i = 0; i < size; i++) {
                sb.append(byteToString(data.get()));
                if (i == size - 1) {
                    sb.append('\n');
                } else {
                    sb.append(' ');
                }
            }

        }
        return sb.toString();
    }

    private static String buildAsciiString(ByteBuffer data) {
        int size = len(data);
        StringBuilder sb = new StringBuilder((BYTES_PER_ROW + 1) * (size / BYTES_PER_ROW + 1));
        while (data.hasRemaining()) {
            size = Math.min(BYTES_PER_ROW, len(data));

            for (int i = 0; i < size; i++) {
                byte ch = data.get();
                if (ch >= '!' && ch <= '~') {
                    sb.append((char) ch);
                } else {
                    sb.append('.');
                }
                if (i == size - 1) {
                    sb.append('\n');
                }
            }

        }
        return sb.toString();
    }


    private final String rowHeaderText;

    private final String bytesText;

    private final String asciiString;

    public HexText(@NotNull ByteBuffer buffer) {
        Objects.requireNonNull(buffer);
        rowHeaderText = buildRowHeaderText(buffer.slice());
        bytesText = buildBytesText(buffer.slice());
        asciiString = buildAsciiString(buffer.slice());
    }

    @NotNull
    public String getRowHeaderText() {
        return rowHeaderText;
    }

    @NotNull
    public String getBytesText() {
        return bytesText;
    }

    @NotNull
    public String getAsciiString() {
        return asciiString;
    }
}
