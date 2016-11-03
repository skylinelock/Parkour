package mc.sky_lock.parkour;

import mc.sky_lock.parkour.command.CommandHandler;
import mc.sky_lock.parkour.json.ParkourConfig;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author sky_lock
 */
public class ParkourPlugin extends JavaPlugin {

    private ParkourManager manager;
    private ParkourConfig config;
    
    @Override
    public void onEnable() {
        this.manager = new ParkourManager(this);
        config = new ParkourConfig(this.getDataFolder());
        
        getCommand("parkour").setExecutor(new CommandHandler(this));
        
        manager.setParkours(config.getParkours());
    }
    
    @Override
    public void onDisable() {
        //ファイルに保存
        config.saveParkours(manager.getParkours());

        //メモリ解放
        manager.removeParkours();
        manager.removeAllTimes();

        manager = null;
    }
    
    public ParkourManager getParkourManager() {
        return manager;
    }
    
    public ParkourConfig getParkourConfig() {
        return config;
    }
    
}
