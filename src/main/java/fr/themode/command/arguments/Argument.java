package fr.themode.command.arguments;

import fr.themode.command.ArgumentCallback;

public abstract class Argument<T> {

    public static final int SUCCESS = 0;
    public static final int UNDEFINED_ERROR = -1;

    private String id;
    private boolean allowSpace;
    private boolean useRemaining;

    private ArgumentCallback callback;

    public Argument(String id, boolean allowSpace, boolean useRemaining) {
        this.id = id;
        this.allowSpace = allowSpace;
        this.useRemaining = useRemaining;
    }

    public Argument(String id, boolean allowSpace) {
        this(id, allowSpace, false);
    }

    public String getId() {
        return id;
    }

    public boolean allowSpace() {
        return allowSpace;
    }

    public boolean useRemaining() {
        return useRemaining;
    }

    public ArgumentCallback getCallback() {
        return callback;
    }

    public void setCallback(ArgumentCallback callback) {
        this.callback = callback;
    }

    public boolean hasErrorCallback() {
        return callback != null;
    }

    public abstract int getCorrectionResult(String value);

    public abstract int getConditionResult(T value);

    public abstract T parse(String value);

}
