package dev.sky_lock.parkour.json;

import com.google.gson.reflect.TypeToken;
import dev.sky_lock.parkour.api.Parkour;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sky_lock
 */

public class ParkourFile {
    private final File file;
    private static final Type type = new TypeToken<List<Parkour>>() {}.getType();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ParkourFile(File dir) {
        dir.mkdirs();
        this.file = new File(dir, "parkours.json");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ParkourのListをJsonとしてFileに保存します。
     *
     * @param parkours ParkourのList
     * @throws IOException IOException
     */
    public void saveParkours(List<Parkour> parkours) throws IOException {
        Type type = new TypeToken<List<Parkour>>() {}.getType();
        GsonUtil.save(file, parkours, type);
    }

    /**
     * Jsonの記述されたFileからParkourのListを読み取ります。
     * 何も記述のない場合、可変の空リストが返されます。
     *
     * @return ParkourのList
     * @throws IOException IOException
     */
    public List<Parkour> loadParkours() throws IOException {
        Type type = new TypeToken<List<Parkour>>() {}.getType();
        List<Parkour> parkours = GsonUtil.load(file, type);

        if (parkours == null) {
            parkours = new ArrayList<>();
        }
        return Collections.unmodifiableList(parkours);
    }
}
