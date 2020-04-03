package fr.themode.command.condition;

public interface CommandCondition<S> {
    boolean apply(S source);
}
