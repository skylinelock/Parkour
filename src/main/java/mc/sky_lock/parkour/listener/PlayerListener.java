package mc.sky_lock.parkour.listener;

import lombok.NonNull;
import mc.sky_lock.parkour.ParkourManager;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class PlayerListener implements Listener {

    private final ParkourPlugin plugin;
    private final ParkourManager manager;

    public PlayerListener(@NonNull ParkourPlugin plugin) {
        this.plugin = plugin;
        this.manager = new ParkourManager(plugin);
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        manager.startMeasure(event);
    }

}
