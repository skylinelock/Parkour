package mc.sky_lock.parkour.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.client.MongoDatabase;
import mc.sky_lock.parkour.ParkourHandler;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Optional;
import java.util.logging.Logger;

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
        Logger logger = handler.getLogger();

        try {
            this.mongoClient = new MongoClient(host, port);
            this.mongoDatabase = mongoClient.getDatabase("parkour");
        } catch (MongoSocketOpenException ex) {
            //TODO エラー文をもっと的確に
            logger.warning("An error occurred while connecting MongoDB");
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
