package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.message.FailureMessage;
import dev.sky_lock.parkour.ParkourLoader;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.ParkourManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
public class ReloadCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("reload")
    @CommandPermission("parkour.command.reload")
    public void onCommand(CommandSender sender) {
        ParkourManager parkourManager = plugin.getParkourManager();
        plugin.reloadResources();
        parkourManager.clearAllParkourPlayers();
        ParkourLoader loader = ParkourPlugin.getInstance().getParkourLoader();

        if (!loader.save()) {
            sender.sendMessage(FailureMessage.RELOAD.getText());
            return;
        }
        if (!loader.load()) {
            sender.sendMessage(FailureMessage.RELOAD.getText());
            return;
        }

        /*MongoDBManager dbManager = handler.getMongoDBManager();
        if (dbManager.getMongoClient() != null) {
            dbManager.close();
        }
        dbManager.connect();*/
        sender.sendMessage(ChatColor.GREEN + "Reload successful");
    }

}