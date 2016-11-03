package mc.sky_lock.parkour;

import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class ParkourPlayer {
    private final Player player;
    private final Parkour parkour;
    private final long startTime;

    public ParkourPlayer(Player player, Parkour parkour) {
        this.player = player;
        this.parkour = parkour;
        this.startTime = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis() - startTime;
    }

    public Parkour getParkour() {
        return parkour;
    }


}
