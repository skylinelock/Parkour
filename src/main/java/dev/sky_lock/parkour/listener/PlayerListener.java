package dev.sky_lock.parkour.listener;

import dev.sky_lock.parkour.Optionals;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.Parkour;
import dev.sky_lock.parkour.api.ParkourManager;
import dev.sky_lock.parkour.api.Runner;
import dev.sky_lock.parkour.api.event.ParkourEvent;
import dev.sky_lock.parkour.api.event.PlayerParkourFailEvent;
import dev.sky_lock.parkour.api.event.PlayerParkourStartEvent;
import dev.sky_lock.parkour.api.event.PlayerParkourSucceedEvent;
import dev.sky_lock.parkour.util.PressurePlate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class PlayerListener implements Listener {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();
    private final ParkourManager parkourManager;
    private final PluginManager pluginManager;

    public PlayerListener() {
        this.parkourManager = plugin.getParkourManager();
        this.pluginManager = plugin.getPluginManager();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        parkourManager.getParkourPlayer(player).ifPresent(parkourManager::remove);
    }

    @EventHandler
    public void onPlayerFail(PlayerMoveEvent event) {
        Location toLoc = event.getTo();
        Location fromLoc = event.getFrom();
        if (sameBlockCoordinate(fromLoc, toLoc)) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.hasPermission("parkour.use")) {
            return;
        }

        FileConfiguration config = plugin.getConfig();
        int height = config.getInt("respawn.height");
        if (toLoc.getBlockY() > height) {
            return;
        }

        Optionals.ifPresentOrElse(parkourManager.getParkourPlayer(player), runner -> {
            Parkour parkour = runner.getParkour();
            if (!callParkourEvent(new PlayerParkourFailEvent(player, parkour))) {
                return;
            }
            player.teleport(parkour.getPresetPoint());
            parkourManager.remove(runner);
            runner.sendFailContents();
        }, () -> {
            if (config.getBoolean("respawn.toSpawn")) {
                player.teleport(player.getWorld().getSpawnLocation());
            }
        });
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location toLoc = event.getTo();
        Location fromLoc = event.getFrom();
        if (sameBlockCoordinate(fromLoc, toLoc)) {
            return;
        }
        Material plate = toLoc.getBlock().getType();
        Arrays.stream(PressurePlate.values()).filter(pressure -> plate == pressure.getMaterial()).findAny().ifPresent(pressure -> {
            parkourManager.getParkours().stream().filter(Parkour::isActive).forEach(parkour -> {
                Player player = event.getPlayer();
                Location start = parkour.getStartPoint();
                Location end = parkour.getEndPoint();
                if (sameBlockCoordinate(toLoc, start)) {
                    if (!callParkourEvent(new PlayerParkourStartEvent(player, parkour))) {
                        return;
                    }
                    parkourManager.getParkourPlayer(player).ifPresent(runner -> {
                        parkourManager.remove(runner);
                        if (!runner.getParkour().equals(parkour)) {
                            runner.sendFailContents();
                        }
                    });
                    Runner runner = new Runner(player, parkour);
                    parkourManager.add(runner);
                    runner.sendStartContents();
                } else if (sameBlockCoordinate(toLoc, end)) {
                    parkourManager.getParkourPlayer(player).ifPresent(runner -> {
                        if (!runner.getParkour().equals(parkour)) {
                            return;
                        }
                        long timeMillis = runner.getTime();
                        if (!callParkourEvent(new PlayerParkourSucceedEvent(player, parkour, timeMillis))) {
                            return;
                        }
                        runner.sendEndContents(timeMillis);
                        parkourManager.remove(runner);
                    });
                }
            });
        });
    }

    private boolean callParkourEvent(ParkourEvent event) {
        pluginManager.callEvent(event);
        return !event.isCancelled();
    }

    private boolean sameBlockCoordinate(Location location1, Location location2) {
        return location1.getBlockX() == location2.getBlockX() && location1.getBlockY() == location2.getBlockY() && location1.getBlockZ() == location2.getBlockZ();
    }

}
