package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.SuccessMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author sky_lock
 */

class AddCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    AddCommand(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.add")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String inputId = args[1];
        Parkour parkour = handler.getParkour(inputId);

        if (parkour == null) {
            Parkour newParkour = new Parkour(inputId);
            handler.addParkour(newParkour);
            player.sendMessage(SuccessMessage.ADD.getText());
            return;
        }
        player.sendMessage(FailedMessage.ID_EXISTS.getText());
    }
}