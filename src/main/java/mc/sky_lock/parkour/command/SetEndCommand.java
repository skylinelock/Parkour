package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

class SetEndCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    SetEndCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.setend")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String inputId = args[1];
        Parkour parkour = handler.getParkourManager().getParkour(inputId);

        if (parkour == null) {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return;
        }

        parkour.setEndPoint(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Set Parkour " + parkour.getId() + "'s endpoint");
    }
}