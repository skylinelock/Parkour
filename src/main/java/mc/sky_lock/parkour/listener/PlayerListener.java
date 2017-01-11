package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.Util;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.api.ParkourPlayer;
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

import java.util.Optional;
import java.util.Set;

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
    public void playerTimer(PlayerMoveEvent event) {
        Location toLocation = event.getTo();
        Location fromLocation = event.getFrom();
        if (compareLocation(fromLocation, toLocation)) {
            return;
        }
        measure(event);
    }

    @EventHandler
    public void playerFail(PlayerMoveEvent event) {

    }

    @EventHandler
    public void playerLogout(PlayerQuitEvent event) {
        Set<ParkourPlayer> parkourPlayers = parkourManager.getParkourPlayers();
        Player player = event.getPlayer();
        parkourManager.getParkourPlayer(player).ifPresent(parkourManager::removeParkourPlayer);
    }

    private void measure(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("parkour.use")) {
            return;
        }
        fail(event);

        Material blockType = event.getTo().getBlock().getType();
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

        PlayerParkourStartEvent startEvent = new PlayerParkourStartEvent(player, parkour);
        pluginManager.callEvent(startEvent);
        if (startEvent.isCancelled()) {
            return;
        }

        Set<ParkourPlayer> parkourPlayers = parkourManager.getParkourPlayers();
        parkourPlayers.stream()
                .filter(parkourPlayer -> parkourPlayer.getPlayer().equals(player))
                .findAny()
                .ifPresent(parkourPlayer -> {
                    parkourPlayers.remove(parkourPlayer);
                    if (!parkourPlayer.getParkour().equals(parkour)) {
                        sendFailedContent(player, parkourPlayer.getParkour());
                    }
                });

        parkourPlayers.add(new ParkourPlayer(player, parkour));

        player.sendMessage("");
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + parkour.getName() + " Parkour challenge started!");
        player.sendMessage("");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
    }

    private void stop(PlayerMoveEvent event, Parkour parkour) {
        Player player = event.getPlayer();
        if (!parkour.isActive()) {
            return;
        }

        Location toLocation = event.getTo();
        Location endPoint = parkour.getEndPoint();
        if (!compareLocation(toLocation, endPoint)) {
            return;
        }
        Set<ParkourPlayer> parkourPlayers = parkourManager.getParkourPlayers();
        parkourPlayers.stream()
                .filter(parkourPlayer -> parkourPlayer.getPlayer().equals(player))
                .filter(parkourPlayer -> parkourPlayer.getParkour().equals(parkour))
                .findAny()
                .ifPresent(parkourPlayer -> {
                    long time_ms = parkourPlayer.getCurrentTimeMillis();

                    PlayerParkourSucceedEvent succeedEvent = new PlayerParkourSucceedEvent(player, parkour, time_ms);
                    pluginManager.callEvent(succeedEvent);
                    if (succeedEvent.isCancelled()) {
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

                    parkourPlayers.remove(parkourPlayer);

                });
    }

    private void fail(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = event.getTo();
        FileConfiguration config = handler.getConfig();
        int teleportHeight = config.getInt("respawn.height");

        if (location.getBlockY() > teleportHeight) {
            return;
        }
        Set<ParkourPlayer> parkourPlayers = parkourManager.getParkourPlayers();

        if (!parkourPlayers.stream().filter(parkourPlayer -> parkourPlayer.getPlayer().equals(player)).findAny().flatMap(parkourPlayer -> {
            Parkour parkour = parkourPlayer.getParkour();

            PlayerParkourFailEvent failEvent = new PlayerParkourFailEvent(player, parkour);
            pluginManager.callEvent(failEvent);
            if (failEvent.isCancelled()) {
                return Optional.of(parkourPlayer);
            }

            player.teleport(parkour.getPresetPoint());
            parkourPlayers.remove(parkourPlayer);
            sendFailedContent(player, parkour);
            return Optional.of(parkourPlayer);
        }).isPresent()) {
            if (!config.getBoolean("respawn.toSpawn")) {
                return;
            }
            player.teleport(player.getWorld().getSpawnLocation());
        }
    }

    private boolean compareLocation(Location location1, Location location2) {
        return location1.getBlockX() == location2.getBlockX() && location1.getBlockY() == location2.getBlockY() && location1.getBlockZ() == location2.getBlockZ();
    }

    private void sendFailedContent(Player player, Parkour parkour) {
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.RED + parkour.getName() + " Parkour challenge failed!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1.0F, 0.0F);
    }


}
