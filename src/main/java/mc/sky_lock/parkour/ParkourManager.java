package mc.sky_lock.parkour;

import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sky_lock
 */

public class ParkourManager {
    private final ParkourHandler handler;
    private final Set<ParkourPlayer> parkourPlayers = new HashSet<>();;
    private List<Parkour> parkours = new ArrayList<>();

    public ParkourManager(ParkourHandler handler) {
        this.handler = handler;
    }

    public void addParkour(Parkour parkour) {
        parkours.add(parkour);
    }

    public void deleteParkour(Parkour parkour) {
        parkours.remove(parkour);
    }

    public List<Parkour> getParkours() {
        return parkours;
    }

    public Parkour getParkour(String id) {
        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(id)) {
                return parkour;
            }
        }
        return null;
    }

    public Set<ParkourPlayer> getParkourPlayers() {
        return parkourPlayers;
    }

    public boolean isParkourPlayer(Player player){
        for (ParkourPlayer parkourPlayer : parkourPlayers) {
            if (parkourPlayer.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public void removeParkourPlayer(ParkourPlayer parkourPlayer) {
        parkourPlayers.remove(parkourPlayer);
    }
}
