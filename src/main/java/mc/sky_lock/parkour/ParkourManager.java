package mc.sky_lock.parkour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mc.sky_lock.parkour.listener.MoveListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author sky_lock
 */
public class ParkourManager {
    private final JavaPlugin plugin;
    private final List<ParkourObj> parkours;
    private final Map<ParkourPlayer, Long> times = new HashMap<>(); 
    
    public ParkourManager(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new MoveListener(this), plugin);
        
        parkours = new ArrayList<>();
    }
    
    public JavaPlugin getPlugin() {
        return plugin;
    }
    
    public void start(ParkourPlayer player) {
        times.put(player, System.currentTimeMillis());
    }
    
    public long stop(ParkourPlayer player) {
       if (!times.containsKey(player)) {
           return -1;
       }
       return System.currentTimeMillis() - times.get(player);
    }
    
    public void setParkours(List<ParkourObj> parkours) {
        this.parkours.forEach(parkour -> parkours.add(parkour) );
    }
    
    public List<ParkourObj> getParkours() {
        return parkours;
    }
    
}
