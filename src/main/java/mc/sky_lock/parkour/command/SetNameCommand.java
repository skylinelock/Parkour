package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * @author sky_lock
 */

class SetNameCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    SetNameCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.rename")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        if (args.length < 3) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String[] nameValues = Arrays.copyOfRange(args, 2, args.length);

        String inputId = args[1];
        Parkour parkour = handler.getParkourManager().getParkour(inputId);

        if (parkour == null) {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
            return;
        }

        String name = String.join(" ", nameValues);
        parkour.setName(name);
        player.sendMessage(ChatColor.GREEN + "Set Parkour " + parkour.getId() + "'s name to " + parkour.getName());
    }
}