package fr.themode.command;

public interface ArgumentCallback<S> {
    void apply(S source, String value, int error);
}
