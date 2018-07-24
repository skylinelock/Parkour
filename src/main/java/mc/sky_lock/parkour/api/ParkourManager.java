package mc.sky_lock.parkour.api;

import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.json.GsonUtil;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author sky_lock
 */

public class ParkourManager {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();
    private final Set<ParkourPlayer> parkourPlayers = new HashSet<>();
    private final List<Parkour> parkours = new ArrayList<>();

    public void addParkour(Parkour parkour) {
        parkours.add(parkour);
    }

    public void deleteParkour(Parkour parkour) {
        parkours.remove(parkour);
    }

    public List<Parkour> getParkours() {
        return Collections.unmodifiableList(parkours);
    }

    public Optional<Parkour> getParkour(String id) {
        return parkours.stream().filter(parkour -> parkour.getId().equals(id)).findAny();
    }

    public void addParkourPlayer(ParkourPlayer parkourPlayer) {
        parkourPlayers.add(parkourPlayer);
    }

    public Set<ParkourPlayer> getParkourPlayers() {
        return Collections.unmodifiableSet(parkourPlayers);
    }

    public Optional<ParkourPlayer> getParkourPlayer(Player player) {
        return parkourPlayers.stream().filter(parkourPlayer -> parkourPlayer.getPlayer().equals(player)).findAny();
    }

    public void removeParkourPlayer(ParkourPlayer parkourPlayer) {
        parkourPlayers.remove(parkourPlayer);
    }

    public boolean isParkourPlayer(Player player) {
        return parkourPlayers.stream().anyMatch(parkourPlayer -> parkourPlayer.getPlayer().equals(player));
    }

    public void clearAllParkour() {
        parkours.clear();
    }

    public void clearAllParkourPlayers() {
        parkourPlayers.clear();
    }

    public void init() {
        File parkourFile = new File(plugin.getDataFolder(), "parkours.json");
        try {
            List<Parkour> loadedParkour = GsonUtil.load(parkourFile, GsonUtil.Types.PARKOURS);
            if (loadedParkour == null) {
                return;
            }
            parkours.addAll(loadedParkour);
        } catch (IOException ex) {
            //後で書く
        }
    }

    public void deinit() {

    }
}
