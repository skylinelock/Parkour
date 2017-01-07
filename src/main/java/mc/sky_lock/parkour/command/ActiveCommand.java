package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.ParkourManager;
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

class ActiveCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    ActiveCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.active")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        if (args.length < 2) {
            player.sendMessage(FailedMessage.NOT_ENOUGH_ARGS.getText());
            return;
        }
        String inputId = args[1];
        ParkourManager parkourManager = handler.getParkourManager();
        if (!parkourManager.getParkour(inputId).flatMap(parkour -> {
            if (!checkParkour(parkour)) {
                player.sendMessage(ParkourMessage.NOT_ENOUGH_ELEMENTS.getText());
                return Optional.of(parkour);
            }

            parkour.setActive(true);
            player.sendMessage(ChatColor.GREEN + "Parkour " + parkour.getId() + " is activated");

            return Optional.of(parkour);
        }).isPresent()) {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
        }
    }

    private boolean checkParkour(Parkour parkour) {
        return parkour.getStartPoint() != null && parkour.getEndPoint() != null && parkour.getPresetPoint() != null && parkour.getName() != null;
    }
}