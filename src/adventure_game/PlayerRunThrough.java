package adventure_game;

import adventure_game.ui.UserInterface;

public class PlayerRunThrough {
    
    public static void performRunThrough(UserInterface iface, Player player){
        iface.sendTextToUser("Starting Game...");
        iface.sendTextToUser("Welcome " + player.getName());
        iface.sendTextToUser("Type help to get started");
        
        while(true){
            String line = iface.getStringFromUser("> ");
            
            if(line.equals("exit")){
                return;
            } else if(line.equals("help")){
                iface.sendTextToUser("  Commands:");
                iface.sendTextToUser("   - help: show this message");
                iface.sendTextToUser("   - exit: end the game");
                
            } else {
                iface.sendTextToUser("Unrecognized Command! type: help");
            }
            
        }
    }
    
}
