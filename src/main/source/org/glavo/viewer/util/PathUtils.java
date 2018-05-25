package org.glavo.viewer.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Objects;

public final class PathUtils {
    private PathUtils() {
    }

    @NotNull
    public static String fileNameOf(@NotNull URI uri) {
        String[] s = uri.toString().split("/");
        return s[s.length - 1];
    }

    @NotNull
    public static String fileNameOf(@NotNull URL url) {
        String[] s = url.toString().split("/");
        return s[s.length - 1];
    }

    @Nullable
    public static String classNameOf(@NotNull URI uri) {
        String fn = fileNameOf(uri);
        if (fn.toLowerCase().equals(".class")) {
            return fn.substring(0, fn.length() - 6);
        } else {
            return null;
        }
    }

    @Nullable
    public static String classNameOf(@NotNull URL url) {
        String fn = fileNameOf(url);
        if (fn.toLowerCase().equals(".class")) {
            return fn.substring(0, fn.length() - 6);
        } else {
            return null;
        }
    }

    @NotNull
    public static URI toUri(@NotNull Path path) {
        try {
            return new URI(URLDecoder.decode(path.toUri().toString(), "UTF-8"));
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            assert false;
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public static URL toUrl(@NotNull Path path) {
        try {
            return new URL(URLDecoder.decode(path.toUri().toString(), "UTF-8"));
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            assert false;
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static Path toPath(@NotNull URI uri) {
        Objects.requireNonNull(uri);
        try {
            return Paths.get(uri);
        } catch (FileSystemNotFoundException e) {
            try {
                FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
                return Paths.get(uri);
            } catch (IOException e1) {
                return null;
            }
        }
    }

    @Nullable
    public static Path toPath(@NotNull URL url) {
        try {
            return toPath(url.toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
