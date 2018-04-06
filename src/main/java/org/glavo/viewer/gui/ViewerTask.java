package org.glavo.viewer.gui;

import javafx.concurrent.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ViewerTask<T> extends Task<T> {

    @NotNull
    public static <T> ViewerTask<T> of(@NotNull Supplier<T> f) {
        return new ViewerTask<T>() {
            @Override
            protected T call() {
                return f.get();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public void onSucceeded(@NotNull Consumer<? super T> callback) {
        Objects.requireNonNull(callback);
        super.setOnSucceeded(
                e -> callback.accept((T) e.getSource().getValue()));
    }

    public void onFailed(@NotNull Consumer<? super Throwable> callback) {
        Objects.requireNonNull(callback);
        super.setOnFailed(event -> {
            Throwable err = event.getSource().getException();
            callback.accept(err);
        });
    }

    public void startInNewThread() {
        new Thread(this).start();
    }
}