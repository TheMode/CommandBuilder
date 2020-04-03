package fr.themode.command;

import fr.themode.command.arguments.Argument;

public class CommandSyntax {

    private Argument[] args;
    private CommandExecutor executor;

    public CommandSyntax(Argument... args) {
        this.args = args;
    }

    public Argument[] getArguments() {
        return args;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

}
