package adventure_game.map;

public class Map {
    
    private final Location startLocation;
    
    public Map() {
        
        // Create Locations
        
        Location villageSquare = new Location("The Village Square");
        villageSquare.setDescription("I think ball games are banned here?");
        this.startLocation = villageSquare;
        
        Location waterfall = new Location("The Village Waterfall");
        waterfall.setDescription("I love listening to the sound of the water");
        
        Location caves = new Location("The Caves");
        caves.setDescription("It's kinda strange that we have caves in such a small village");
        
        // Join Locations Together
        joinLocationsVertically(waterfall, villageSquare);
        joinLocationsHorizontally(waterfall, caves);
    }
    
    private void joinLocationsHorizontally(Location west, Location east){
        east.west = west;
        west.east = east;
    }
    
    private void joinLocationsVertically(Location north, Location south){
        north.south = south;
        south.north = north;
    }
    
    public Location getStartLocation() {
        return startLocation;
    }
    
}
