package mc.sky_lock.parkour;

import mc.sky_lock.parkour.api.Parkour;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author sky_lock
 */

public class ParkourPlayer {
    private final Player player;
    private final Parkour parkour;
    private final long startTime;

    public ParkourPlayer(@NotNull Player player, @NotNull Parkour parkour) {
        this.player = player;
        this.parkour = parkour;

        this.startTime = System.currentTimeMillis();
    }

    long getCurrentTimeMillis() {
        long currentTime = System.currentTimeMillis();
        return currentTime - startTime;
    }

    public Player getPlayer() {
        return player;
    }

    public Parkour getParkour() {
        return parkour;
    }

}
