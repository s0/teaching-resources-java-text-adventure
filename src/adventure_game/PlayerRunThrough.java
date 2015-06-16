package adventure_game;

import adventure_game.map.Location;
import adventure_game.map.Map;
import adventure_game.ui.UserInterface;

public class PlayerRunThrough {
    
    private final UserInterface iface;
    private final Player player;
    private final Map map;
    private Location currentLocation;
    
    public PlayerRunThrough(UserInterface iface, Player player) {
        this.iface = iface;
        this.player = player;
        
        map = new Map();
        currentLocation = map.getStartLocation();
    }
    
    public void performRunThrough() {
        iface.sendTextToUser("Starting Game...");
        iface.sendTextToUser("Welcome " + player.getName());
        iface.sendTextToUser("Type help to get started");
        
        while (true) {
            String line = iface.getStringFromUser("> ");
            
            if (line.equals("exit")) {
                return;
            }
            
            if (line.equals("help")) {
                iface.sendTextToUser("  Commands:");
                iface.sendTextToUser("   - help: show this message");
                iface.sendTextToUser("   - exit: end the game");
                iface.sendTextToUser("   - look around: inspect the area around you");
                iface.sendTextToUser("   - move: move your character");
                continue;
            }
            
            if (line.equals("look around")) {
                lookAround();
                continue;
            }
            
            if (line.equals("move")) {
                move();
                continue;
            }
            
            iface.sendTextToUser("Unrecognized Command! type: help");
            
        }
    }
    
    private void lookAround() {
        // Talk about current location
        iface.sendTextToUser("  You Look around...");
        iface.sendTextToUser("  You are at " + currentLocation.name);
        String description = currentLocation.getDescription();
        if (description != null)
            iface.sendTextToUser("    '" + description + "'");
        
        // Describe the locations around you.
        if (currentLocation.north != null)
            iface.sendTextToUser("  North of you is: " + currentLocation.north.name);
        if (currentLocation.east != null)
            iface.sendTextToUser("  East of you is: " + currentLocation.east.name);
        if (currentLocation.south != null)
            iface.sendTextToUser("  South of you is: " + currentLocation.south.name);
        if (currentLocation.west != null)
            iface.sendTextToUser("  West of you is: " + currentLocation.west.name);
    }
    
    private void move() {
        String direction = iface.getStringFromUser("  Enter a Direction (n,e,s,w) > ");
        
        if (direction.equals("n")) {
            if (currentLocation.north != null)
                currentLocation = currentLocation.north;
            else
                iface.sendTextToUser("There is nothing north of you");
            return;
        }
        
        if (direction.equals("e")) {
            if (currentLocation.east != null)
                currentLocation = currentLocation.east;
            else
                iface.sendTextToUser("There is nothing east of you");
            return;
        }
        
        if (direction.equals("s")) {
            if (currentLocation.south != null)
                currentLocation = currentLocation.south;
            else
                iface.sendTextToUser("There is nothing south of you");
            return;
        }
        
        if (direction.equals("w")) {
            if (currentLocation.west != null)
                currentLocation = currentLocation.west;
            else
                iface.sendTextToUser("There is nothing west of you");
            return;
        }
        
        iface.sendTextToUser("Unregognized Direction!");
    }
    
}
