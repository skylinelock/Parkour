package mc.sky_lock.parkour;

import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.json.ParkourFile;
import org.bukkit.ChatColor;

import java.io.IOException;

/**
 * @author sky_lock
 */

public class ParkourLoader {

    private final ParkourManager manager;
    private final ParkourFile file;
    private final PluginLogger logger;

    public ParkourLoader(ParkourFile file, ParkourManager manager) {
        this.file = file;
        this.logger = ParkourPlugin.getInstance().getPluginLogger();
        this.manager = manager;
    }

    public boolean load() {
        try {
            file.loadParkours().forEach(manager::add);
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "parkours.jsonからのParkourの読み込みに失敗しました");
            return false;
        }
        return true;
    }

    public boolean save() {
        try {
            file.saveParkours(manager.getParkours());
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "parkours.jsonへのParkourの保存に失敗しました");
            return false;
        } finally {
            manager.clearAllParkours();
        }
        return true;
    }
}
