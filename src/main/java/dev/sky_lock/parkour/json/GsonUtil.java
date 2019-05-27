package dev.sky_lock.parkour.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.sky_lock.parkour.api.Parkour;
import org.bukkit.Location;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author sky_lock
 */

public class GsonUtil {

    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Location.class, new LocationAdapter())
            .create();

    /**
     * Fileから指定した型でJsonをロードします。
     * Fileに何も記述がない場合、nullを返します。
     *
     * @param type ロード対象の型
     * @param <T> 型
     * @return Jsonから読み取られた型
     * @throws IOException
     * @throws RuntimeException
     */
    public static <T> T load(File file, Type type) throws IOException {
        checkFile(file);
        try (JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            if (!reader.hasNext()) {
                return null;
            }
            return gson.fromJson(reader, type);
        }
    }

    /**
     * Fileに指定した型のオブジェクトをJsonとして保存します。
     *
     * @param obj 保存するインスタンス
     * @param type 保存する型
     * @param <T> 型
     * @throws IOException
     * @throws RuntimeException
     */
    public static <T> void save(File file, T obj, Type type) throws IOException {
        checkFile(file);
        try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            gson.toJson(obj, type, writer);
        }
    }

    private static void checkFile(File file) throws IOException {
        if (!file.canRead()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public static class Types {
        public static Type PARKOURS = new TypeToken<List<Parkour>>() {}.getType();
    }
}
