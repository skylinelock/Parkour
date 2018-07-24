package mc.sky_lock.parkour.database;

import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.connection.ClusterSettings;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.api.ParkourPlayer;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author sky_lock
 */

public class MongoDatabase {

    private final ParkourPlugin plugin;
    private final MongoClient mongoClient;
    private final com.mongodb.async.client.MongoDatabase mongoDatabase;

    public MongoDatabase(ParkourPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfig();

        String host = config.getString("mongodb.host");
        int port = config.getInt("mongodb.port");

        ServerAddress address = new ServerAddress(host, port);

        ClusterSettings clusterSettings = ClusterSettings.builder().hosts(Arrays.asList(address)).build();
        MongoClientSettings clientSettings = MongoClientSettings.builder().clusterSettings(clusterSettings).build();
        this.mongoClient = MongoClients.create(clientSettings);
        this.mongoDatabase = mongoClient.getDatabase("parkour");
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public void savePlayerRecord(ParkourPlayer parkourPlayer, long record) {
        MongoCollection<Document> mongoCollection = getCollection("playerRecord");
        Document document = new Document("player", parkourPlayer.getPlayer().getUniqueId())
                .append("parkour", parkourPlayer.getParkour().getId())
                .append("record", record);
        mongoCollection.insertOne(document, (t, e) -> {
            if (e != null) {
                e.printStackTrace();
            }
        });
    }

    public CompletionStage<Long> findPlayerRecord(Player player, Parkour parkour) {
        MongoCollection<Document> mongoCollection = getCollection("playerRecord");
        Document document = new Document("player", player.getUniqueId()).append("parkour", parkour.getId());
        CompletableFuture<Long> result = new CompletableFuture<>();
        mongoCollection.find(document).first((t, e) -> {
            if (e != null) {
                e.printStackTrace();
                result.completeExceptionally(e);
                return;
            }
            result.complete(t.getLong("record"));
        });
        return result;
    }

    private MongoCollection<Document> getCollection(String collectionName) {
        return mongoDatabase.getCollection(collectionName);
    }
}
