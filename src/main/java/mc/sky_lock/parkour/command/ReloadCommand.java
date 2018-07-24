package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.ParkourPlugin;
import mc.sky_lock.parkour.PluginLogger;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.json.ParkourFile;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

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
        plugin.reloadConfig();

        parkourManager.clearAllParkourPlayers();

        ParkourFile parkourFile = plugin.getParkourFile();
        try {
            parkourFile.saveParkours(parkourManager.getParkours());
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "parkours.jsonへのParkourの保存に失敗しました");
            sender.sendMessage(FailedMessage.RELOAD.getText());
            return;
        } finally {
            parkourManager.clearAllParkour();
        }

        try {
            parkourFile.loadParkours().forEach(parkourManager::addParkour);
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "parkours.jsonからのParkourの読み込みに失敗しました");
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