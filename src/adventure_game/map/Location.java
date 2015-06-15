package adventure_game.map;

public class Location {
    
    public final String name;
    private String description;
    
    public Location north;
    public Location east;
    public Location south;
    public Location west;
    
    public Location(String name){
        this.name = name;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return this.description;
    }
    
}
