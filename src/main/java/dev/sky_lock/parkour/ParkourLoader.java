package dev.sky_lock.parkour;

import dev.sky_lock.parkour.api.ParkourManager;
import dev.sky_lock.parkour.json.ParkourFile;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author sky_lock
 */

public class ParkourLoader {

    private final ParkourManager manager;
    private final ParkourFile file;
    private final Logger logger;

    public ParkourLoader(ParkourFile file, ParkourManager manager) {
        this.file = file;
        this.logger = ParkourPlugin.getInstance().getLogger();
        this.manager = manager;
    }

    public boolean load() {
        try {
            file.loadParkours().forEach(manager::add);
        } catch (IOException ex) {
            logger.warning("parkours.jsonからのParkourの読み込みに失敗しました");
            return false;
        }
        return true;
    }

    public boolean save() {
        try {
            file.saveParkours(manager.getParkours());
        } catch (IOException ex) {
            logger.warning("parkours.jsonへのParkourの保存に失敗しました");
            return false;
        } finally {
            manager.clearAllParkours();
        }
        return true;
    }
}
