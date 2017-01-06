package mc.sky_lock.parkour.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.client.MongoDatabase;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.PluginLogger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Optional;

/**
 * @author sky_lock
 */

public class MongoDBManager {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private ParkourHandler handler;

    public MongoDBManager(ParkourHandler handler) {
        this.handler = handler;
    }

    public void connect() {
        handler.reloadConfig();
        FileConfiguration config = handler.getConfig();

        String host = config.getString("mongodb.host");
        int port = config.getInt("mongodb.port");
        PluginLogger logger = handler.getLogger();

        try {
            this.mongoClient = new MongoClient(host, port);
            this.mongoDatabase = mongoClient.getDatabase("parkour");
        } catch (MongoSocketOpenException ex) {
            logger.out(ChatColor.RED + "MongoDBへの接続に失敗しました");
            handler.disablePlugin();
        }
    }

    public void close() {
        if (Optional.ofNullable(mongoClient).isPresent()) {
            mongoClient.close();
        }
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
