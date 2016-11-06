package mc.sky_lock.parkour;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;
import java.util.Map;

/**
 * @author sky_lock
 */
public class ParkourManager {
    private final ParkourPlugin plugin;
    private final PlayerMoveEvent event;

    private final Player player;
    private final Location location;

    private final Map<Player, Long> timeMap;
    private final Map<Player, Parkour> parkourMap;

    public ParkourManager(ParkourPlugin plugin, PlayerMoveEvent event, Map<Player, Long> timeMap, Map<Player, Parkour> parkourMap) {
        this.plugin = plugin;

        this.event = event;
        this.player = event.getPlayer();
        this.location = event.getTo();

        this.timeMap = timeMap;
        this.parkourMap = parkourMap;
    }

    public void start() {
        List<Parkour> parkours = plugin.getParkours();
        for (Parkour parkour : parkours) {
            Location startPoint = parkour.getStartPoint();
            if (!compareLocation(location, startPoint)) {
                continue;
            }
            if (!parkour.isActive()) {
                return;
            }
            if (timeMap.containsKey(player) && parkourMap.containsKey(player) && !parkourMap.get(player).equals(parkour)) {
                sendFailedContent(player, parkourMap.get(player));
            }
            timeMap.put(player, System.currentTimeMillis());
            parkourMap.put(player, parkour);

            player.sendMessage("");
            player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + parkour.getName() + " Parkour challenge started!");
            player.sendMessage("");

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
        }
    }

    public void stop() {
        List<Parkour> parkours = plugin.getParkours();
        for (Parkour parkour : parkours) {
            Location endPoint = parkour.getEndPoint();
            if (!compareLocation(location, endPoint)) {
                continue;
            }
            if (!timeMap.containsKey(player)) {
                return;
            }
            if (!parkourMap.containsKey(player)) {
                return;
            }
            if (!parkourMap.get(player).equals(parkour)) {
                return;
            }
            player.sendMessage("");
            player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + parkour.getName() + " Parkour challenge succeeded!");
            player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + "Total Time: " + timeMap.get(player));
            player.sendMessage("");

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

            timeMap.remove(player);
            parkourMap.remove(player);
        }
    }

    public void respawn() {
        if (!parkourMap.containsKey(player)) {
            return;
        }
        if (!timeMap.containsKey(player)) {
            return;
        }
        if (location.getBlockY() > -25) {
            return;
        }
        Parkour parkour = parkourMap.get(player);
        player.teleport(parkour.getRespawnPoint());

        sendFailedContent(player, parkour);

        timeMap.remove(player);
        parkourMap.remove(player);
    }

    private boolean compareLocation(Location location1, Location location2) {
        if (location1.getBlockX() != location2.getBlockX() || location1.getBlockY() != location2.getBlockY() || location1.getBlockZ() != location2.getBlockZ()) {
            return false;
        }
        return true;
    }

    private void sendFailedContent(Player player, Parkour parkour) {
        player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.RED + parkour.getName() + " Parkour challenge failed!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1.0F, 0.0F);
    }

}
