package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class SaveCommand implements ICommand {
    private final ParkourHandler handler;

    public SaveCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.save")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String inputId = args[1];
        for (Parkour parkour : handler.getParkours()) {
            if (parkour.getId().equals(inputId)) {
                if (parkour.canSave()) {
                    parkour.setSave(false);
                } else {
                    parkour.setSave(true);
                }
                player.sendMessage(SuccessMessage.SAVE.getText());
                return;
            }
        }
        player.sendMessage(FailedMessage.SAVE.getText());
    }
}
