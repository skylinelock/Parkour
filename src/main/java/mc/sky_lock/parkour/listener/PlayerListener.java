package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.Util;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.api.ParkourPlayer;
import mc.sky_lock.parkour.api.event.ParkourEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourFailEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourStartEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourSucceedEvent;
import mc.sky_lock.parkour.database.TimeDocument;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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
            sendFailedContent(player, parkour);
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
                sendFailedContent(player, parkourPlayer.getParkour());
            }
        });

        parkourManager.addParkourPlayer(new ParkourPlayer(player, parkour));

        player.sendMessage("");
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + parkour.getName() + " Parkour challenge started!");
        player.sendMessage("");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
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
            long time_ms = parkourPlayer.getCurrentTimeMillis();
            if (!callParkourEvent(new PlayerParkourSucceedEvent(player, parkour, time_ms))) {
                return;
            }

            String time = Util.formatTime(time_ms);
            player.sendMessage("");
            player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + parkour.getName() + " Parkour challenge succeeded!");
            player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + "Total Time: " + time);
            player.sendMessage("");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

            if (parkour.canSave()) {
                TimeDocument scoreDocument = new TimeDocument(handler, parkourPlayer);
                scoreDocument.insertDocument(time_ms);
            }

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

    private void sendFailedContent(Player player, Parkour parkour) {
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.RED + parkour.getName() + " Parkour challenge failed!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1.0F, 0.0F);
    }


}
