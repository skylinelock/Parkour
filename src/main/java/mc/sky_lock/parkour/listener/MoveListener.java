package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;

/**
 * @author sky_lock
 */
public class MoveListener implements Listener {

    private final ParkourManager manager;

    public MoveListener(ParkourManager manager) {
        this.manager = manager;
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void moveListener(PlayerMoveEvent event) {
        Location toLoc = event.getTo();
        Location fromLoc = event.getFrom();
        if (toLoc.getBlockX() == fromLoc.getBlockX() && toLoc.getBlockY() == fromLoc.getBlockY() && toLoc.getBlockZ() == fromLoc.getBlockZ()) {
            return;
        }

        Material underType = toLoc.getBlock().getType();
        Player player = event.getPlayer();
        Map<Player, Long> times = manager.getTimes();

        if (underType != Material.IRON_PLATE && underType != Material.GOLD_PLATE
                && underType != Material.WOOD_PLATE && underType != Material.STONE_PLATE) {
            return;
        }
        for (Parkour parkour : manager.getParkours()) {
            Location startLoc = parkour.getStartLocation();
            Location endLoc = parkour.getEndLocation();

            if (startLoc != null && startLoc.getBlockX() == toLoc.getBlockX() && startLoc.getBlockY() == toLoc.getBlockY() && startLoc.getBlockZ() == toLoc.getBlockZ()) {
                manager.start(player);

                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1F, 1F);
                player.sendMessage("");
                player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + parkour.getName() + " Parkour challenge started!");
                player.sendMessage("");

            } else if (endLoc != null && endLoc.getBlockX() == toLoc.getBlockX() && endLoc.getBlockY() == toLoc.getBlockY() && endLoc.getBlockZ() == toLoc.getBlockZ()) {
                if (!times.containsKey(player)) {
                    return;
                }
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);

                player.sendMessage("");
                player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + parkour.getName() + " Parkour challenge succeeded!");
                player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + "Total Time: " + manager.stop(player));
                player.sendMessage("");
            }
            continue;
        }

    }

    /*private String convertTime(Long mili) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("m:s");
        return format.format(mili);
    }*/
}
