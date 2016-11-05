package mc.sky_lock.parkour;

import mc.sky_lock.parkour.command.Commands;
import mc.sky_lock.parkour.json.ParkourConfig;
import mc.sky_lock.parkour.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 *
 * @author sky_lock
 */
public class ParkourPlugin extends JavaPlugin {
    private ParkourConfig config;
    private List<Parkour> parkours;
    
    @Override
    public void onEnable() {
        config = new ParkourConfig(this.getDataFolder());
        parkours = config.getParkours();
        
        getCommand("parkour").setExecutor(new Commands(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

    }
    
    @Override
    public void onDisable() {
        config.saveParkours(parkours);
    }
    
    public ParkourConfig getParkourConfig() {
        return config;
    }

    public List<Parkour> getParkours() {
        return parkours;
    }

    
}
