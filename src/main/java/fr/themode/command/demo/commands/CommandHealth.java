package fr.themode.command.demo.commands;

import fr.themode.command.Arguments;
import fr.themode.command.Command;
import fr.themode.command.arguments.Argument;
import fr.themode.command.arguments.ArgumentType;
import fr.themode.command.arguments.number.ArgumentNumber;
import fr.themode.command.demo.Player;

public class CommandHealth extends Command<Player> {

    public CommandHealth() {
        super("health", "healthbar", "h");
        // Alternatively
        // super("health");

        setCondition(this::condition);

        setDefaultExecutor(this::defaultExecutor);

        Argument arg0 = ArgumentType.Word("mode").from("set", "add");

        Argument arg1 = ArgumentType.Integer("value").between(0, 100);

        addCallback(this::modeCallback, arg0);
        addCallback(this::valueCallback, arg1);

        addSyntax(this::execute, arg0);
        addSyntax(this::execute2, arg0, arg1);
    }

    private boolean condition(Player player) {
        // Your custom condition, called no matter the syntax used
        boolean hasPerm = true;
        if (!hasPerm) {
            System.out.println("You do not have permission !");
            return false;
        }
        return true;
    }

    private void defaultExecutor(Player player, Arguments args) {
        System.out.println("Correct usage: health [set/add] [number]");
    }

    private void modeCallback(Player player, String value, int error) {
        System.out.println("SYNTAX ERROR: '" + value + "' should be replaced by 'set' or 'add'");
    }

    private void valueCallback(Player player, String value, int error) {
        switch (error) {
            case ArgumentNumber.NOT_NUMBER_ERROR:
                System.out.println("SYNTAX ERROR: '" + value + "' isn't a number!");
                break;
            case ArgumentNumber.RANGE_ERROR:
                System.out.println("SYNTAX ERROR: " + value + " is not between 0 and 100");
                break;
        }
    }

    private void execute(Player player, Arguments args) {
        System.out.println("/health " + args.getWord("mode") + " [Integer]");
    }

    private void execute2(Player player, Arguments args) {
        String mode = args.getWord("mode");
        int value = args.getInteger("value");

        switch (mode.toLowerCase()) {
            case "set":
                player.setHealth(value);
                break;
            case "add":
                player.setHealth(player.getHealth() + value);
                break;
        }
        System.out.println("You have now " + player.getHealth() + " health");
    }

}
