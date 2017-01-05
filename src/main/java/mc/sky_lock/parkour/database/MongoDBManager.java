package mc.sky_lock.parkour.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

/**
 * @author sky_lock
 */

public class MongoDBManager {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public void connect() {
        this.mongoClient = new MongoClient();
        this.mongoDatabase = mongoClient.getDatabase("parkour");
    }

    public void close() {
        if(Optional.ofNullable(mongoClient).isPresent()) {
            mongoClient.close();
        }
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
