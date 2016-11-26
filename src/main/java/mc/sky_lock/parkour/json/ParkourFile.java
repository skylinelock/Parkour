package mc.sky_lock.parkour.json;

import com.google.gson.reflect.TypeToken;
import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.exception.EmptyJsonException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sky_lock
 */

public class ParkourFile {

    private final GsonFile gsonFile;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ParkourFile(File dir) {
        dir.mkdirs();
        File file = new File(dir, "parkours.json");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        gsonFile = new GsonFile(file);
    }

    /**
     * ParkourのListをJsonとしてFileに保存します。
     *
     * @param parkours ParkourのList
     */
    public void saveParkours(List<Parkour> parkours) throws IOException {
        Type type = new TypeToken<List<Parkour>>() {}.getType();
        gsonFile.save(parkours, type);
    }

    /**
     * Jsonの記述されたFileからParkourのListを読み取ります。
     * 何も記述のない場合、可変の空リストが返されます。
     *
     * @return ParkourのList
     * @throws IOException
     */
    public List<Parkour> loadParkours() throws IOException {
        Type type = new TypeToken<List<Parkour>>() {}.getType();
        List<Parkour> parkours;
        try {
            parkours = gsonFile.load(type);
        } catch (EmptyJsonException ex) {
            parkours = new ArrayList<>();
        }
        return parkours;
    }
}
