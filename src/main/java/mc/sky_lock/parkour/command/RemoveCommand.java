package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.Parkour;
import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author sky_lock
 */

class RemoveCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    RemoveCommand(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        List<Parkour> parkours = handler.getParkours();
        String inputid = args[1];

        for (Parkour parkour : parkours) {
            if (parkour.getId().equals(inputid)) {
                parkours.remove(parkour);
                player.sendMessage(SuccessMessage.REMOVE.getText());
                return;
            }
        }

        player.sendMessage(FailedMessage.REMOVE.getText());
    }
}
