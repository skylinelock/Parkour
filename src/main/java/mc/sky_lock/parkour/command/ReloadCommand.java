package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.json.ParkourFile;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author sky_lock
 */

public class ReloadCommand implements ICommand {
    private final ParkourHandler handler;

    ReloadCommand(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("parkour.command.reload")) {
            sender.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        Logger logger = handler.getLogger();
        ParkourFile parkourFile = handler.getParkourFile();
        try {
            parkourFile.saveParkours(handler.getParkours());
        } catch (IOException ex) {
            logger.warning("An error occurred while saving parkours");
            sender.sendMessage(FailedMessage.RELOAD.getText());
            return;
        }
        handler.getParkourPlayers().clear();

        List<Parkour> parkours = handler.getParkours();
        parkours.clear();
        try {
            parkours.addAll(parkourFile.loadParkours());
        } catch (IOException ex) {
            logger.warning("An error occurred while loading parkours");
            sender.sendMessage(FailedMessage.RELOAD.getText());
            return;
        }
        sender.sendMessage(SuccessMessage.RELOAD.getText());
    }

}
