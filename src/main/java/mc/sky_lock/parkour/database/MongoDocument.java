package mc.sky_lock.parkour.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mc.sky_lock.parkour.ParkourHandler;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * @author sky_lock
 */

public class MongoDocument {
    private final MongoDatabase mongoDatabase;
    private final MongoCollection mongoCollection;

    public MongoDocument(ParkourHandler handler, String collectionName) {
        mongoDatabase = handler.getMongoDatabase();
        mongoCollection = mongoDatabase.getCollection(collectionName);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public void insertOne(Document document) {
        mongoCollection.insertOne(document);
    }

    public void updateOne(Bson bson, Bson bson1) {
        mongoCollection.updateOne(bson, bson1);
    }
}
