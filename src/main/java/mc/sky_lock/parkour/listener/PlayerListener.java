package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourManager;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author sky_lock
 */
public class PlayerListener implements Listener {

    private final ParkourPlugin plugin;

    public PlayerListener(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void moveListener(PlayerMoveEvent event) {
        ParkourManager manager = new ParkourManager(plugin, event);

        manager.respawn();

        Location to = event.getTo();
        Location from = event.getFrom();

        if (from.getBlockX() == to.getBlockX() || from.getBlockY() == to.getBlockY() || from.getBlockZ() == from.getBlockZ()) {
            return;
        }

        manager.start();
        manager.stop();
    }
}
