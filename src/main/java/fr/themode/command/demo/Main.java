package fr.themode.command.demo;

import fr.themode.command.CommandDispatcher;
import fr.themode.command.demo.commands.CommandHealth;
import fr.themode.command.demo.commands.CommandMessage;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();

        CommandDispatcher<Player> dispatcher = new CommandDispatcher<>();
        dispatcher.register(new CommandHealth());
        dispatcher.register(new CommandMessage());

        dispatcher.execute(player, "msg 1 je suis un string");

        /*Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            dispatcher.execute(player, cmd);
        }*/
    }

}
