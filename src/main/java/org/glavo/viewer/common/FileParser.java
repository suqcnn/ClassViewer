package org.glavo.viewer.common;

public interface FileParser<Component extends FileComponent> {
    Component parse(byte[] data);
}
