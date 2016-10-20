package mc.sky_lock.parkour;

import org.bukkit.Location;

/**
 *
 * @author sky_lock
 */
public class ParkourObj {
    private final String name;
    public Location startLocation;
    public Location endLocation;
    public Location respawnLocation;
    
    public ParkourObj(String name) {
        this.name = name;
    }
    
    public void setStartLocation(Location loc) {
        startLocation = loc;
    }
    
    public void setEndLocation(Location loc) {
        this.endLocation = loc;
    }
    
    public void setRespawnLocation(Location loc) {
        this.respawnLocation = loc;
    }
    
    public Location getStartLocation() {
        return startLocation;
    }
    
    public Location getEndLocation() {
        return endLocation;
    }
    
    public Location getRespawnLocation() {
        return respawnLocation;
    }
    
    public String getName() {
        return name;
    }
}
