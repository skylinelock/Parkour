package dev.sky_lock.parkour.api.event;

import dev.sky_lock.parkour.api.Parkour;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class PlayerParkourFailEvent extends ParkourEvent {

    public PlayerParkourFailEvent(Player player, Parkour parkour) {
        super(player, parkour);
    }

}
