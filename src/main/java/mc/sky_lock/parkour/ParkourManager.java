package mc.sky_lock.parkour;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
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

    private final List<Parkour> parkours;

    private Map<Player, Long> timeMap = new HashMap<>();
    private Map<Player, Parkour> parkourMap = new HashMap<>();

    public ParkourManager(ParkourPlugin plugin, PlayerMoveEvent event) {
        this.plugin = plugin;
        this.event = event;
        this.player = event.getPlayer();
        this.location = player.getLocation();

        this.parkours = plugin.getParkours();
    }

    public void start() {
        for (Parkour parkour : parkours) {
            Location startPoint = parkour.getStartPoint();
            if (startPoint == null || compareLocation(startPoint, location)) {
                return;
            }
            if (parkourMap.containsKey(player) && parkourMap.get(player).equals(parkour)) {
                sendFailedContent(player, parkour);
                timeMap.remove(player);
                parkourMap.remove(player);
            }
            timeMap.put(player, System.currentTimeMillis());
            parkourMap.put(player, parkour);

            player.sendMessage("");
            player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + parkour.getName() + " Parkour challenge started!");
            player.sendMessage("");

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
        }
    }

    public void stop() {
        for (Parkour parkour : parkours) {
            Location endPoint = parkour.getEndPoint();
            if (endPoint == null || compareLocation(endPoint, location)) {
                return;
            }
            if (timeMap.containsKey(player) && parkourMap.containsKey(player)) {
                continue;
            }
            if (!parkourMap.get(player).equals(parkour)) {
                continue;
            }
            player.sendMessage("");
            player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + parkour.getName() + " Parkour challenge succeeded!");
            player.sendMessage("");

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        }
    }

    public void respawn() {
        if (location.getBlockY() > 0) {
            return;
        }
        if (timeMap.get(player) == null && parkourMap.get(player) == null) {
            return;
        }
        sendFailedContent(player, parkourMap.get(player));

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
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
    }

}
