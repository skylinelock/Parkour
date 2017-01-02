package mc.sky_lock.parkour.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mc.sky_lock.parkour.ParkourHandler;
import org.bson.Document;

/**
 * @author sky_lock
 */

public abstract class MongoDocument {
    private final MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;

    public MongoDocument(ParkourHandler handler, String collectionName) {
        mongoDatabase = handler.getDatabase();
        mongoCollection = mongoDatabase.getCollection(collectionName);
    }

    protected MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    protected MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }
}
