package mc.sky_lock.parkour.api.event;

import mc.sky_lock.parkour.api.Parkour;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class PlayerParkourStartEvent extends ParkourEvent {

    public PlayerParkourStartEvent(Player player, Parkour parkour) {
        super(player, parkour);
    }

}
