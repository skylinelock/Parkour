package mc.sky_lock.parkour.api;

import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author sky_lock
 */

public class ParkourManager {

    private final Set<ParkourPlayer> parkourPlayers = new HashSet<>();
    private List<Parkour> parkours = new ArrayList<>();

    public void addParkour(Parkour parkour) {
        parkours.add(parkour);
    }

    public void deleteParkour(Parkour parkour) {
        parkours.remove(parkour);
    }

    public List<Parkour> getParkours() {
        return parkours;
    }

    public Optional<Parkour> getParkour(String id) {
        return parkours.stream().filter(parkour -> parkour.getId().equals(id)).findAny();
    }

    public Set<ParkourPlayer> getParkourPlayers() {
        return parkourPlayers;
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
}
