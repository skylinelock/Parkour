package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.ParkourPlugin;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
public class RecordCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("record")
    @CommandPermission("parkour.command.record")
    public void onCommand(Player player, String id) {
        /*ParkourManager parkourManager = handler.getParkourManager();
        MongoDBManager dbManager = handler.getMongoDBManager();
        String parkourName = args[2];
        parkourManager.getParkour(parkourName).map(parkour -> {
            MongoCollection<Document> mongoCollection = dbManager.getMongoDatabase().getCollection("playerRecord");
            Document findTarget = new Document("")
            mongoCollection.
        }).orElseGet();*/
    }
}
