package mc.sky_lock.parkour.json;

import com.google.common.reflect.TypeToken;
import mc.sky_lock.parkour.Parkour;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author sky_lock
 */
public class ParkourConfig {

    private final GsonConfig gsonConf;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ParkourConfig(File dir) {
        dir.mkdirs();
        File file = new File(dir, "parkours.json");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        gsonConf = new GsonConfig(file);
    }

    public void saveParkours(List<Parkour> parkours) {
        Type type = new TypeToken<List<Parkour>>() {
        }.getType();
        try {
            gsonConf.save(parkours, type);
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public List<Parkour> getParkours() {
        Type type = new TypeToken<List<Parkour>>() {
        }.getType();
        List<Parkour> parkours = null;
        try {
            parkours = gsonConf.load(List.class, type);
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
        }
        return parkours;
    }
}
