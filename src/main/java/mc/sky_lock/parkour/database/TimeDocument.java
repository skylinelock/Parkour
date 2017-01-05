package mc.sky_lock.parkour.database;

import com.mongodb.client.MongoCollection;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourPlayer;
import org.bson.Document;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class TimeDocument {
    private final MongoDocument mongoDocument;
    private final Player player;
    private final Parkour parkour;
    private final MongoCollection mongoCollection;

    public TimeDocument(ParkourHandler handler, ParkourPlayer parkourPlayer) {
        mongoDocument = new MongoDocument(handler, "playerScore");
        player = parkourPlayer.getPlayer();
        parkour = parkourPlayer.getParkour();
        mongoCollection = mongoDocument.getMongoCollection();
    }

    public void insertDocument(long time) {
        Document document = new Document("uuid", player.getUniqueId().toString())
                .append("name", player.getName())
                .append("parkour", parkour.getId())
                .append("time_ms", time);
        mongoDocument.insertOne(document);
    }

    /*public void updateDocument(long time) {
        for (Document document : mongoCollection.find()) {
            Document uuid = new Document("uuid", player.getUniqueId());
            Document

        }
    }*/

}
