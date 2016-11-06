package mc.sky_lock.parkour;

import org.bukkit.Location;

/**
 * @author sky_lock
 */
public class Parkour {
    private final String id;
    private boolean active = false;
    private String name;
    private Location startLocation;
    private Location endLocation;
    private Location respawnLocation;

    public Parkour(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartPoint(Location loc) {
        startLocation = loc;
    }

    public void setEndPoint(Location loc) {
        endLocation = loc;
    }

    public void setRespawnPoint(Location loc) {
        respawnLocation = loc;
    }

    public String getName() {
        return name;
    }

    public Location getStartPoint() {
        return startLocation;
    }

    public Location getEndPoint() {
        return endLocation;
    }

    public Location getRespawnPoint() {
        return respawnLocation;
    }

    public void setActive(boolean flag) {
        active = flag;
    }

    public boolean isActive() {
        return active;
    }

    public String getId() {
        return id;
    }

}
