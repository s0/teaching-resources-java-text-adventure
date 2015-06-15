package adventure_game;

import adventure_game.ui.CommandLineUserInterface;
import adventure_game.ui.UserInterface;

public class Game {
    
    public static void main(String[] args) {
        UserInterface iface = new CommandLineUserInterface();
        
        String name = iface.getStringFromUser("What is your name?");
        
        Player player = new Player(name);
        PlayerRunThrough.performRunThrough(iface, player);
            
        iface.sendTextToUser("Game finished");
    }
    
}
