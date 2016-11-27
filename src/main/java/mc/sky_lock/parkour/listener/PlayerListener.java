package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.ParkourManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class PlayerListener implements Listener {

    private final ParkourHandler handler;
    private final ParkourManager manager;

    public PlayerListener(@NotNull ParkourHandler handler) {
        this.handler = handler;
        this.manager = new ParkourManager(handler);
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        manager.measure(event);
    }

}
