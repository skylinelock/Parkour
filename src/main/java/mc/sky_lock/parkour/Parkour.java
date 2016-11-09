package mc.sky_lock.parkour;

import lombok.Data;
import lombok.NonNull;
import org.bukkit.Location;

/**
 * @author sky_lock
 */

@Data
public class Parkour {

    @NonNull
    private final String id;
    private boolean active = false;
    private String name;
    private Location startPoint;
    private Location endPoint;
    private Location respawnPoint;
}
