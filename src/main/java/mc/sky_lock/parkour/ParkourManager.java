package mc.sky_lock.parkour;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sky_lock
 */
public class ParkourManager {
    private final ParkourPlugin plugin;

    private final Map<Player, Long> timeMap = new HashMap<>();
    private final Map<Player, Parkour> parkourMap = new HashMap<>();

    private PlayerMoveEvent event;

    public ParkourManager(ParkourPlugin plugin) {
        this.plugin = plugin;
    }

    public void startMeasure(PlayerMoveEvent event) {
        this.event = event;

        respawn();
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
        start();
        stop();
    }

    private void start() {
        Player player = event.getPlayer();
        Location location = event.getTo();
        List<Parkour> parkours = plugin.getParkours();

        for (Parkour parkour : parkours) {
            if (!parkour.isActive()) {
                continue;
            }
            Location startPoint = parkour.getStartPoint();
            if (!compareLocation(location, startPoint)) {
                continue;
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

    private void stop() {
        Player player = event.getPlayer();
        Location location = event.getTo();
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
            player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.YELLOW + "Total Time: " + timeFormat(System.currentTimeMillis() - timeMap.get(player)));
            player.sendMessage("");

            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

            timeMap.remove(player);
            parkourMap.remove(player);
        }
    }

    private void respawn() {
        Player player = event.getPlayer();
        Location location = event.getTo();
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
        return location1.getBlockX() == location2.getBlockX() && location1.getBlockY() == location2.getBlockY() && location1.getBlockZ() == location2.getBlockZ();
    }

    private void sendFailedContent(Player player, Parkour parkour) {
        player.sendMessage(ChatColor.GOLD + "[Parkour] " + ChatColor.RED + parkour.getName() + " Parkour challenge failed!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1.0F, 0.0F);
    }

    private String timeFormat(Long time) {
        SimpleDateFormat timeformat = new SimpleDateFormat("mm:ss.SSS");
        return timeformat.format(time);
    }

}
