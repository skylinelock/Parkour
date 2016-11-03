package mc.sky_lock.parkour;

import org.bukkit.Location;

/**
 *
 * @author sky_lock
 */
public class Parkour {
    private final String id;
    private boolean active = false;
    private String name;
    public Location startLocation;
    public Location endLocation;
    public Location respawnLocation;
    
    public Parkour(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setStartLocation(Location loc) {
        startLocation = loc;
    }
    
    public void setEndLocation(Location loc) {
        endLocation = loc;
    }
    
    public void setRespawnLocation(Location loc) {
        respawnLocation = loc;
    }

    public String getName() {
        return name;
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

    public void setActive(boolean flag) {
        active = flag;
    }

    public boolean getActive() {
        return active;
    }

    public boolean isActive() {
        return active;
    }

    public String getId() {
        return id;
    }
}
