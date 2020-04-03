package fr.themode.command;

public interface CommandExecutor<S> {
    void apply(S source, Arguments args);
}