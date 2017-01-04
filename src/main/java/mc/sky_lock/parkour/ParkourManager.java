package mc.sky_lock.parkour;

import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourPlayer;
import mc.sky_lock.parkour.api.event.PlayerParkourFailEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourStartEvent;
import mc.sky_lock.parkour.api.event.PlayerParkourSucceedEvent;
import mc.sky_lock.parkour.config.ConfigElement;
import mc.sky_lock.parkour.database.PlayerScoreDocument;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * @author sky_lock
 */

public class ParkourManager {
    private final ParkourHandler handler;
    private final Set<ParkourPlayer> parkourPlayers;

    public ParkourManager(@NotNull ParkourHandler handler) {
        this.handler = handler;
        this.parkourPlayers = handler.getParkourPlayers();
    }

    public void measure(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("parkour.use")) {
            return;
        }
        Location toLocation = event.getTo();
        Location fromLocation = event.getFrom();

        fail(event);
        if (compareLocation(fromLocation, toLocation)) {
            return;
        }
        Material blockType = toLocation.getBlock().getType();
        if (blockType != Material.GOLD_PLATE &&
                blockType != Material.IRON_PLATE &&
                blockType != Material.WOOD_PLATE &&
                blockType != Material.STONE_PLATE) {
            return;
        }

        handler.getParkours().forEach(parkour -> {
            start(event, parkour);
            stop(event, parkour);
        });
    }

    private void start(PlayerMoveEvent event, Parkour parkour) {
        Player player = event.getPlayer();
        if (!parkour.isActive()) {
            return;
        }
        Location toLocation = event.getTo();
        Location startPoint = parkour.getStartPoint();

        if (!compareLocation(toLocation, startPoint)) {
            return;
        }

        PlayerParkourStartEvent startEvent = new PlayerParkourStartEvent(player, parkour);
        handler.getPluginManager().callEvent(startEvent);
        if (startEvent.isCancelled()) {
            return;
        }

        for (ParkourPlayer parkourPlayer : parkourPlayers) {
            if (parkourPlayer.getParkour().equals(parkour)) {
                parkourPlayers.remove(parkourPlayer);
                break;
            }
            if (parkourPlayer.getPlayer().equals(player)) {
                parkourPlayers.remove(parkourPlayer);
                sendFailedContent(player, parkourPlayer.getParkour());
                break;
            }
        }
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
        for (ParkourPlayer parkourPlayer : parkourPlayers) {
            if (parkourPlayer.getPlayer().equals(player)) {
                if (!parkourPlayer.getParkour().equals(parkour)) {
                    continue;
                }
                long time_ms = parkourPlayer.getCurrentTimeMillis();

                PlayerParkourSucceedEvent succeedEvent = new PlayerParkourSucceedEvent(player, parkour, time_ms);
                handler.getPluginManager().callEvent(succeedEvent);
                if (succeedEvent.isCancelled()) {
                    return;
                }

                String time = DurationFormatUtils.formatDurationHMS(time_ms);
                player.sendMessage("");
                player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + parkour.getName() + " Parkour challenge succeeded!");
                player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + "Total Time: " + time);
                player.sendMessage("");

                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

                if (parkour.canSave()) {
                    PlayerScoreDocument scoreDocument = new PlayerScoreDocument(handler, parkourPlayer);
                    scoreDocument.insertDocument(time_ms);
                }

                parkourPlayers.remove(parkourPlayer);
            }
        }
    }

    private void fail(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = event.getTo();
        int teleportHeight = handler.getConfigFile().loadTeleportInt(ConfigElement.TELEPORT_HEIGHT);
        if (location.getBlockY() > teleportHeight) {
            return;
        }

        for (ParkourPlayer parkourPlayer : parkourPlayers) {
            if (parkourPlayer.getPlayer().equals(player)) {
                Parkour parkour = parkourPlayer.getParkour();

                PlayerParkourFailEvent failEvent = new PlayerParkourFailEvent(player, parkour);
                handler.getPluginManager().callEvent(failEvent);
                if (failEvent.isCancelled()) {
                    return;
                }

                player.teleport(parkour.getPresetPoint());
                parkourPlayers.remove(parkourPlayer);
                sendFailedContent(player, parkour);
            }
        }
    }

    private boolean compareLocation(Location location1, Location location2) {
        return location1.getBlockX() == location2.getBlockX() && location1.getBlockY() == location2.getBlockY() && location1.getBlockZ() == location2.getBlockZ();
    }

    private void sendFailedContent(Player player, Parkour parkour) {
        player.sendMessage(ChatColor.DARK_AQUA+ "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.RED + parkour.getName() + " Parkour challenge failed!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1.0F, 0.0F);
    }

}
