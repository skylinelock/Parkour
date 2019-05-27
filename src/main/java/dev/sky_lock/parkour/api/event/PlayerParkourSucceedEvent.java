package dev.sky_lock.parkour.api.event;

import dev.sky_lock.parkour.api.Parkour;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class PlayerParkourSucceedEvent extends ParkourEvent {

    private final long timeMillis;

    public PlayerParkourSucceedEvent(Player player, Parkour parkour, long timeMillis) {
        super(player, parkour);
        this.timeMillis = timeMillis;
    }

    public long getTimeMillis() {
        return timeMillis;
    }
}
