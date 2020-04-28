package fr.themode.command.demo.commands;

import fr.themode.command.Arguments;
import fr.themode.command.Command;
import fr.themode.command.arguments.Argument;
import fr.themode.command.arguments.ArgumentType;
import fr.themode.command.demo.Player;

import java.util.Arrays;

public class CommandMessage extends Command<Player> {
    public CommandMessage() {
        super("msg");


        Argument arg0 = ArgumentType.Integer("int");
        Argument arg1 = ArgumentType.StringArray("array");

        setDefaultExecutor((source, args) -> System.out.println("default executor"));

        addSyntax(this::execute, arg0);
        addSyntax(this::execute, arg0, arg1);

    }

    private void execute(Player player, Arguments args) {
        System.out.println("test: " + Arrays.toString(args.getStringArray("array")));
    }
}
