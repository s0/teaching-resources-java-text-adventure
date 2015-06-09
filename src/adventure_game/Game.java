package adventure_game;

import adventure_game.ui.CommandLineUserInterface;
import adventure_game.ui.UserInterface;

public class Game {
    
    public static void main(String[] args) {
        UserInterface iface = new CommandLineUserInterface();
        iface.sendTextToUser("Welcome... Wait...");
        String name = iface.getStringFromUser("What is your name?");
        iface.sendTextToUser("Welcome " + name + "!");
    }
    
}
