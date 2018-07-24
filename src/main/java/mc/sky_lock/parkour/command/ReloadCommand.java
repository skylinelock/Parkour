package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.ParkourLoader;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.PluginLogger;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author sky_lock
 */

@CommandAlias("parkour|pk")
public class ReloadCommand extends BaseCommand {

    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("reload")
    @CommandPermission("parkour.command.reload")
    public void onCommand(CommandSender sender) {
        PluginLogger logger = plugin.getPluginLogger();
        ParkourManager parkourManager = plugin.getParkourManager();
        plugin.reloadResources();
        parkourManager.clearAllParkourPlayers();
        ParkourLoader loader = ParkourPlugin.getInstance().getParkourLoader();

        if (!loader.save()) {
            sender.sendMessage(FailedMessage.RELOAD.getText());
            return;
        }
        if (!loader.load()) {
            sender.sendMessage(FailedMessage.RELOAD.getText());
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