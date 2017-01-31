package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.ParkourManager;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import mc.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sky_lock
 */

class AddCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;
    private static final String NAME = "add";

    AddCommand(ParkourHandler handler) {
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
        ParkourManager parkourManager = handler.getParkourManager();
        String inputId = args[1];

        if (!parkourManager.getParkour(inputId).flatMap(parkour -> {
            player.sendMessage(ParkourMessage.ALREADY_EXISTS.getText());
            return Optional.of(parkour);
        }).isPresent()) {
            Parkour newParkour = new Parkour(inputId);
            parkourManager.addParkour(newParkour);
            player.sendMessage(ChatColor.GREEN + "Parkour " + newParkour.getId() + " added");
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}