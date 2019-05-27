package dev.sky_lock.parkour.api;

import dev.sky_lock.parkour.ParkourPlugin;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author sky_lock
 */

public class ParkourManager {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();
    private final Set<Runner> runners = new HashSet<>();
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

    public void add(Runner runner) {
        runners.add(runner);
    }

    public Set<Runner> getRunners() {
        return Collections.unmodifiableSet(runners);
    }

    public Optional<Runner> getParkourPlayer(Player player) {
        return runners.stream().filter(runner -> runner.getPlayer().equals(player)).findAny();
    }

    public void remove(Runner runner) {
        runners.remove(runner);
    }

    public boolean isParkourPlayer(Player player) {
        return runners.stream().anyMatch(runner -> runner.getPlayer().equals(player));
    }

    public void clearAllParkours() {
        parkours.clear();
    }

    public void clearAllParkourPlayers() {
        runners.clear();
    }
}
