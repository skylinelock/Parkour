package mc.sky_lock.parkour.api;

import mc.sky_lock.parkour.ParkourPlugin;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author sky_lock
 */

public class ParkourManager {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();
    private final Set<ParkourPlayer> parkourPlayers = new HashSet<>();
    private final List<Parkour> parkours = new ArrayList<>();

    public void add(Parkour parkour) {
        parkours.add(parkour);
    }

    public void delete(Parkour parkour) {
        parkours.remove(parkour);
    }

    public List<Parkour> getParkours() {
        return Collections.unmodifiableList(parkours);
    }

    public Optional<Parkour> getParkour(String id) {
        return parkours.stream().filter(parkour -> parkour.getId().equals(id)).findAny();
    }

    public void add(ParkourPlayer parkourPlayer) {
        parkourPlayers.add(parkourPlayer);
    }

    public Set<ParkourPlayer> getParkourPlayers() {
        return Collections.unmodifiableSet(parkourPlayers);
    }

    public Optional<ParkourPlayer> getParkourPlayer(Player player) {
        return parkourPlayers.stream().filter(parkourPlayer -> parkourPlayer.getPlayer().equals(player)).findAny();
    }

    public void remove(ParkourPlayer parkourPlayer) {
        parkourPlayers.remove(parkourPlayer);
    }

    public boolean isParkourPlayer(Player player) {
        return parkourPlayers.stream().anyMatch(parkourPlayer -> parkourPlayer.getPlayer().equals(player));
    }

    public void clearAllParkours() {
        parkours.clear();
    }

    public void clearAllParkourPlayers() {
        parkourPlayers.clear();
    }
}
