package mc.sky_lock.parkour;

import mc.sky_lock.parkour.listener.MoveListener;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sky_lock
 */
public class ParkourManager {

    private final ParkourPlugin plugin;
    private List<Parkour> parkours = new ArrayList<>();
    private Map<Player, Long> times = new HashMap<>();

    public ParkourManager(ParkourPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new MoveListener(this), plugin);
    }

    public void start(Player player) {
        if (times.containsKey(player)) {

        }
        times.put(player, System.currentTimeMillis());
    }

    public long stop(Player player) {
        if (!times.containsKey(player)) {
            return -1;
        }
        long time = System.currentTimeMillis() - times.get(player);
        times.remove(player);
        return time;
    }
    
    public void addParkour(String id) {
        Parkour parkour = new Parkour(id);
        parkours.add(parkour);
    }
    
    public boolean removeParkour(String id) {
        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(id)) {
                parkours.remove(parkour);
                return true;
            }
        }
        return false;
    }

    public void setParkours(List<Parkour> parkours) {
        this.parkours = parkours;
    }

    public List<Parkour> getParkours() {
        return parkours;
    }

    public void removeAllTimes() {
        times = null;
    }

    public void removeParkours() {
        parkours = null;
    }

    public Map<Player, Long> getTimes() {
        return times;
    }

}
