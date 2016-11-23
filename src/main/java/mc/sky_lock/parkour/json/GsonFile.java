package mc.sky_lock.parkour.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.NonNull;
import org.bukkit.Location;

import java.io.*;
import java.lang.reflect.Type;

/**
 *
 * @author sky_lock
 */
class GsonFile {

    private final Gson gson;
    private final File file;

    GsonFile(@NonNull File file) {
        this.file = file;
        this.gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Location.class, new LocationAdapter())
                .create();
    }

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
    @SuppressWarnings("unused")
    <T> T load(Type type) throws IOException, RuntimeException {
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

    /**
     * Fileに指定した型のオブジェクトをJsonとして保存します。
     *
     * @param obj 保存するインスタンス
     * @param type 保存する型
     * @param <T> 型
     * @throws IOException
     * @throws RuntimeException
     */
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
