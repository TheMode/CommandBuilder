package fr.themode.command;

import fr.themode.command.arguments.Argument;
import fr.themode.command.condition.CommandCondition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Command<S> {

    private String name;
    private String[] aliases;

    private CommandExecutor defaultExecutor;
    private CommandCondition condition;
    private List<CommandSyntax> syntaxes;

    public Command(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;

        this.syntaxes = new ArrayList<>();
    }

    public Command(String name) {
        this(name, new String[0]);
    }

    public CommandCondition getCondition() {
        return condition;
    }

    public void addCallback(ArgumentCallback<S> callback, Argument argument) {
        argument.setCallback(callback);
    }

    public void addSyntax(CommandExecutor<S> executor, Argument... args) {
        CommandSyntax syntax = new CommandSyntax(args);
        syntax.setExecutor(executor);
        this.syntaxes.add(syntax);
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public CommandExecutor getDefaultExecutor() {
        return defaultExecutor;
    }

    public void setDefaultExecutor(CommandExecutor<S> executor) {
        this.defaultExecutor = executor;
    }

    public void setCondition(CommandCondition<S> commandCondition) {
        this.condition = commandCondition;
    }

    public Collection<CommandSyntax> getSyntaxes() {
        return syntaxes;
    }
}
