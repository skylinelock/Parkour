package mc.sky_lock.parkour;

import java.util.ArrayList;
import java.util.List;
import mc.sky_lock.parkour.command.CommandHandler;
import mc.sky_lock.parkour.json.ParkourConfig;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author sky_lock
 */
public class Parkour extends JavaPlugin {
    private ParkourManager manager;
    private ParkourConfig config;
    
    @Override
    public void onEnable() {
        this.manager = new ParkourManager(this);
        config = new ParkourConfig(this.getDataFolder());
        
        getCommand("parkour").setExecutor(new CommandHandler(this));
        
        config.saveParkours(test());
        
        manager.setParkours(config.getParkours());
    }
    
    @Override
    public void onDisable() {
        manager = null;
    }
    
    public List<ParkourObj> test() {
        List<ParkourObj> list = new ArrayList<>();
        list.add(new ParkourObj("hage"));
        list.add(new ParkourObj("hage2"));
        list.add(new ParkourObj("hage3"));
        return list;
    }
    
    public ParkourManager getParkourManager() {
        return manager;
    }
    
}
