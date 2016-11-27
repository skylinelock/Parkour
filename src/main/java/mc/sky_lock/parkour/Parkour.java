package mc.sky_lock.parkour;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author sky_lock
 */

public class Parkour {

    private final String id;
    private boolean active = false;
    private String name;
    private Location startPoint;
    private Location endPoint;
    private Location presetPoint;

    public Parkour(@NotNull String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(@Nullable Location startPoint) {
        this.startPoint = startPoint;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(@Nullable Location endPoint) {
        this.endPoint = endPoint;
    }

    public Location getPresetPoint() {
        return presetPoint;
    }

    public void setPresetPoint(@Nullable Location presetPoint) {
        this.presetPoint = presetPoint;
    }

}
