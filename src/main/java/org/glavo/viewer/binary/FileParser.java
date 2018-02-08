package org.glavo.viewer.binary;

public interface FileParser<Component extends FileComponent> {
    Component parse(byte[] data);
}
