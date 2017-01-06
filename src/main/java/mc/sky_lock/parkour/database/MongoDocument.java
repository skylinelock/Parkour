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

    MongoDocument(ParkourHandler handler, String collectionName) {
        mongoDatabase = handler.getMongoDBManager().getMongoDatabase();
        mongoCollection = mongoDatabase.getCollection(collectionName);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    void insertOne(Document document) {
        mongoCollection.insertOne(document);
    }

    public void updateOne(Bson bson, Bson bson1) {
        mongoCollection.updateOne(bson, bson1);
    }
}
