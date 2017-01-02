package mc.sky_lock.parkour.database;

import com.mongodb.client.MongoCollection;
import mc.sky_lock.parkour.ParkourHandler;
import org.bson.Document;

/**
 * @author sky_lock
 */

public class PlayerTimeDocument extends MongoDocument {
    private MongoCollection<Document> mongoCollection;

    public PlayerTimeDocument(ParkourHandler handler) {
        super(handler, "playerTime");
        this.mongoCollection = super.getMongoCollection();
    }
}
