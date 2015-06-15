package adventure_game;

import adventure_game.map.Location;
import adventure_game.map.Map;
import adventure_game.ui.UserInterface;

public class PlayerRunThrough {
    
    public static void performRunThrough(UserInterface iface, Player player){
        iface.sendTextToUser("Starting Game...");
        iface.sendTextToUser("Welcome " + player.getName());
        iface.sendTextToUser("Type help to get started");
        
        Map map = new Map();
        Location currentLocation = map.getStartLocation();
        
        while(true){
            String line = iface.getStringFromUser("> ");
            
            if(line.equals("exit")){
                return;
            }
            
            if(line.equals("help")){
                iface.sendTextToUser("  Commands:");
                iface.sendTextToUser("   - help: show this message");
                iface.sendTextToUser("   - exit: end the game");
                iface.sendTextToUser("   - look around: inspect the area around you");
                iface.sendTextToUser("   - move: move your character");
                continue;
            }
                
            if(line.equals("look around")){
                lookAround(iface, currentLocation);
                continue;
            }
            
            if(line.equals("move")){
                String direction =  iface.getStringFromUser("  Enter a Direction (n,e,s,w) > ");
                
                if(direction.equals("n")){
                    if(currentLocation.north != null)
                        currentLocation = currentLocation.north;
                    else
                        iface.sendTextToUser("There is nothing north of you");
                    continue;
                }
                
                if(direction.equals("e")){
                    if(currentLocation.east != null)
                        currentLocation = currentLocation.east;
                    else
                        iface.sendTextToUser("There is nothing east of you");
                    continue;
                }
                
                if(direction.equals("s")){
                    if(currentLocation.south != null)
                        currentLocation = currentLocation.south;
                    else
                        iface.sendTextToUser("There is nothing south of you");
                    continue;
                }
                
                if(direction.equals("w")){
                    if(currentLocation.west != null)
                        currentLocation = currentLocation.west;
                    else
                        iface.sendTextToUser("There is nothing west of you");
                    continue;
                }

                iface.sendTextToUser("Unregognized Direction!");
                continue;
            }
            
            iface.sendTextToUser("Unrecognized Command! type: help");
            
        }
    }
    
    private static void lookAround(UserInterface iface, Location location){
        // Talk about current location
        iface.sendTextToUser("  You Look around...");
        iface.sendTextToUser("  You are at " + location.name);
        String description = location.getDescription();
        if(description != null)
            iface.sendTextToUser("    '" + description + "'");
        
        // Describe the locations around you.
        if(location.north != null)
            iface.sendTextToUser("  North of you is: " + location.north.name);
        if(location.east != null)
            iface.sendTextToUser("  East of you is: " + location.east.name);
        if(location.south != null)
            iface.sendTextToUser("  South of you is: " + location.south.name);
        if(location.west != null)
            iface.sendTextToUser("  West of you is: " + location.west.name);
    }
    
}
