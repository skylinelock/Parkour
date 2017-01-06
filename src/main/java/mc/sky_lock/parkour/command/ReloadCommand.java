package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.ParkourManager;
import mc.sky_lock.parkour.PluginLogger;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.database.MongoDBManager;
import mc.sky_lock.parkour.json.ParkourFile;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.util.List;

/**
 * @author sky_lock
 */

public class ReloadCommand implements ICommand {
    private final ParkourHandler handler;

    ReloadCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("parkour.command.reload")) {
            sender.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        PluginLogger logger = handler.getLogger();
        ParkourManager parkourManager = handler.getParkourManager();
        handler.reloadConfig();

        ParkourFile parkourFile = handler.getParkourFile();
        try {
            parkourFile.saveParkours(parkourManager.getParkours());
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "Parkour情報のparkours.jsonへの保存に失敗しました");
            sender.sendMessage(FailedMessage.RELOAD.getText());
            return;
        }
        parkourManager.getParkourPlayers().clear();

        List<Parkour> parkours = parkourManager.getParkours();
        parkours.clear();
        try {
            parkours.addAll(parkourFile.loadParkours());
        } catch (IOException ex) {
            logger.out(ChatColor.RED + "parkours.jsonからのParkourの読み込みに失敗しました");
            sender.sendMessage(FailedMessage.RELOAD.getText());
            return;
        }
        MongoDBManager dbManager = handler.getMongoDBManager();
        if (dbManager.getMongoClient() != null) {
            dbManager.close();
        }
        dbManager.connect();
        sender.sendMessage(SuccessMessage.RELOAD.getText());
    }

}