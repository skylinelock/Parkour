package mc.sky_lock.parkour;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author sky_lock
 */
public class ParkourPlugin extends JavaPlugin {

    private ParkourHandler handler;

    @Override
    public void onEnable() {
        handler = new ParkourHandler(this);
        handler.onEnable();

    }
    
    @Override
    public void onDisable() {
        handler.onDisable();
        handler = null;
    }

}
