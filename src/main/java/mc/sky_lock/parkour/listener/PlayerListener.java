package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.api.Runner;
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

        parkourManager.getParkourPlayer(player).map(runner -> {
            Parkour parkour = runner.getParkour();

            if (!callParkourEvent(new PlayerParkourFailEvent(player, parkour))) {
                return runner;
            }

            player.teleport(parkour.getPresetPoint());
            parkourManager.remove(runner);
            runner.sendFailContents();
            return runner;
        }).orElseGet(() -> {
            if (config.getBoolean("respawn.toSpawn")) {
                player.teleport(player.getWorld().getSpawnLocation());
            }
            return null;
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
        switch (plate) {
            case HEAVY_WEIGHTED_PRESSURE_PLATE:
            case LIGHT_WEIGHTED_PRESSURE_PLATE:
            case ACACIA_PRESSURE_PLATE:
            case BIRCH_PRESSURE_PLATE:
            case DARK_OAK_PRESSURE_PLATE:
            case JUNGLE_PRESSURE_PLATE:
            case OAK_PRESSURE_PLATE:
            case SPRUCE_PRESSURE_PLATE:
            case STONE_PRESSURE_PLATE:
                break;
            default: return;
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
        if (!sameBlockCoordinate(toLocation, startPoint)) {
            return;
        }
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
    }

    private void stop(PlayerMoveEvent event, Parkour parkour) {
        Player player = event.getPlayer();
        Location toLoc = event.getTo();
        Location endPoint = parkour.getEndPoint();
        if (!sameBlockCoordinate(toLoc, endPoint)) {
            return;
        }
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

    private boolean callParkourEvent(ParkourEvent event) {
        pluginManager.callEvent(event);
        return !event.isCancelled();
    }

    private boolean sameBlockCoordinate(Location location1, Location location2) {
        return location1.getBlockX() == location2.getBlockX() && location1.getBlockY() == location2.getBlockY() && location1.getBlockZ() == location2.getBlockZ();
    }

}
