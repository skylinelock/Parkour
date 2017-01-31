package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.ParkourManager;
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

class SetPreCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;
    private static final String NAME = "setpre";

    SetPreCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.setpre")) {
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
            parkour.setPresetPoint(player.getLocation());
            player.sendMessage(ChatColor.GREEN + "Set Parkour " + parkour.getId() + "'s prepoint");
            return Optional.of(parkour);
        }).isPresent()) {
            player.sendMessage(ParkourMessage.NOT_FOUND.getText());
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}