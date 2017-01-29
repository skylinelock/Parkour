package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.api.ParkourPlayer;
import mc.sky_lock.parkour.api.event.ParkourEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourFailEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourStartEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourSucceedEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class PlayerListener implements Listener {
    private final ParkourHandler handler;
    private final ParkourManager parkourManager;
    private final PluginManager pluginManager;

    public PlayerListener(ParkourHandler handler) {
        this.handler = handler;
        this.parkourManager = handler.getParkourManager();
        this.pluginManager = handler.getPluginManager();
    }

    @EventHandler
    public void playerLogout(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        parkourManager.getParkourPlayer(player).ifPresent(parkourManager::removeParkourPlayer);
    }

    @EventHandler
    public void playerFail(PlayerMoveEvent event) {
        Location toLoc = event.getTo();
        Location fromLoc = event.getFrom();
        if (compareLocation(fromLoc, toLoc)) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.hasPermission("parkour.use")) {
            return;
        }
        FileConfiguration config = handler.getConfig();

        int heightLimit = config.getInt("respawn.height");
        if (toLoc.getBlockY() > heightLimit) {
            return;
        }

        parkourManager.getParkourPlayer(player).map(parkourPlayer -> {
            Parkour parkour = parkourPlayer.getParkour();

            if (!callParkourEvent(new PlayerParkourFailEvent(player, parkour))) {
                return parkourPlayer;
            }

            player.teleport(parkour.getPresetPoint());
            parkourManager.removeParkourPlayer(parkourPlayer);
            parkourPlayer.sendFailContents();
            return parkourPlayer;
        }).orElseGet(() -> {
            if (config.getBoolean("respawn.toSpawn")) {
                player.teleport(player.getWorld().getSpawnLocation());
            }
            return null;
        });
    }

    @EventHandler
    public void playerMeasure(PlayerMoveEvent event) {
        Location toLoc = event.getTo();
        Location fromLoc = event.getFrom();
        if (compareLocation(fromLoc, toLoc)) {
            return;
        }

        Material blockType = toLoc.getBlock().getType();
        if (blockType != Material.GOLD_PLATE &&
                blockType != Material.IRON_PLATE &&
                blockType != Material.WOOD_PLATE &&
                blockType != Material.STONE_PLATE) {
            return;
        }

        parkourManager.getParkours().stream().filter(Parkour::isActive).forEach(parkour -> {
            start(event, parkour);
            stop(event, parkour);
        });
    }

    private void start(PlayerMoveEvent event, Parkour parkour) {
        Player player = event.getPlayer();
        Location toLocation = event.getTo();
        Location startPoint = parkour.getStartPoint();

        if (!compareLocation(toLocation, startPoint)) {
            return;
        }

        if (!callParkourEvent(new PlayerParkourStartEvent(player, parkour))) {
            return;
        }

        parkourManager.getParkourPlayer(player).ifPresent(parkourPlayer -> {
            parkourManager.removeParkourPlayer(parkourPlayer);
            if (!parkourPlayer.getParkour().equals(parkour)) {
                parkourPlayer.sendFailContents();
            }
        });

        ParkourPlayer parkourPlayer = new ParkourPlayer(player, parkour);
        parkourManager.addParkourPlayer(parkourPlayer);
        parkourPlayer.sendStartContents();
    }

    private void stop(PlayerMoveEvent event, Parkour parkour) {
        Player player = event.getPlayer();
        Location toLoc = event.getTo();
        Location endPoint = parkour.getEndPoint();

        if (!compareLocation(toLoc, endPoint)) {
            return;
        }

        parkourManager.getParkourPlayer(player).ifPresent(parkourPlayer -> {
            if (!parkourPlayer.getParkour().equals(parkour)) {
                return;
            }
            long timeMillis = parkourPlayer.getCurrentTimeMillis();
            if (!callParkourEvent(new PlayerParkourSucceedEvent(player, parkour, timeMillis))) {
                return;
            }
            parkourPlayer.sendEndContents(timeMillis);
            parkourManager.removeParkourPlayer(parkourPlayer);
        });
    }

    private boolean callParkourEvent(ParkourEvent event) {
        pluginManager.callEvent(event);
        return !event.isCancelled();
    }

    private boolean compareLocation(Location location1, Location location2) {
        return location1.getBlockX() == location2.getBlockX() && location1.getBlockY() == location2.getBlockY() && location1.getBlockZ() == location2.getBlockZ();
    }

}
