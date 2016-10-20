package mc.sky_lock.parkour.json;

import com.google.common.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import mc.sky_lock.parkour.ParkourObj;

/**
 *
 * @author sky_lock
 */
public class ParkourConfig {
    private final GsonConfig gsonConf;

    public ParkourConfig(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File file = new File(dir, "parkours.json");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        gsonConf = new GsonConfig(file);
    }
    
    public void saveParkours(List<ParkourObj> parkours) {
        try {
            gsonConf.save(parkours, List.class);
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
        }      
    }
    
    public List<ParkourObj> getParkours() {
        Type type = new TypeToken<List<ParkourObj>>() {}.getType();
        List<ParkourObj> parkours = new ArrayList<>();
        try {
            parkours = gsonConf.load(List.class, type);
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
        }

        return parkours;
    }
}
