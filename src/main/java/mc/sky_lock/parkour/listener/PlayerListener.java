package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourManager;
import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class PlayerListener implements Listener {


    private final ParkourPlugin plugin;
    private final Map<Player, Parkour> parkourMap = new HashMap<>();
    private final Map<Player, Long> timeMap = new HashMap<>();

    public PlayerListener(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void moveListener(PlayerMoveEvent event) {
        ParkourManager manager = new ParkourManager(plugin, event, timeMap, parkourMap);

        manager.respawn();
        Location to = event.getTo();
        Location from = event.getFrom();
        if (to.getBlockX() == from.getBlockX() && to.getBlockY() == from.getBlockY() && to.getBlockZ() == from.getBlockZ()) {
            return;
        }
        Material blockType = to.getBlock().getType();
        if (blockType != Material.GOLD_PLATE &&
                blockType != Material.IRON_PLATE &&
                blockType != Material.WOOD_PLATE &&
                blockType != Material.STONE_PLATE) {
            return;
        }

        manager.start();
        manager.stop();
    }

}
