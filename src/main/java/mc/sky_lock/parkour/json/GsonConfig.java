package mc.sky_lock.parkour.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Location;

import java.io.*;
import java.lang.reflect.Type;

/**
 *
 * @author sky_lock
 */
class GsonConfig {

    private final Gson gson;
    private final File file;

    GsonConfig(File file) {
        this.file = file;
        this.gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Location.class, new LocationAdapter())
                .create();
    }

    @SuppressWarnings("unused")
    <T> T load(Class<T> clazz, Type type) throws IOException, RuntimeException {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            if (!reader.hasNext()) {
                return null;
            }
            return gson.fromJson(reader, type);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    <T> void save(T obj, Type type) throws IOException, RuntimeException {
        JsonWriter writer = null;
        try {
            writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            gson.toJson(obj, type, writer);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
