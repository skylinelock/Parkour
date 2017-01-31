package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author sky_lock
 */

class ListCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;
    private static final String NAME = "list";

    ListCommand(ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.list")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        List<Parkour> parkours = handler.getParkourManager().getParkours();

        player.sendMessage(ChatColor.GREEN + "------  [" + ChatColor.WHITE + "Parkour List" + ChatColor.GREEN + "]  ------");

        parkours.stream()
                .filter(Parkour::isActive)
                .forEach(parkour -> player.sendMessage(
                        ChatColor.GREEN + "Id : " + ChatColor.WHITE + parkour.getId() + ChatColor.GREEN + " Name : " + ChatColor.WHITE + parkour.getName()
                        )
                );
        parkours.stream()
                .filter(parkour -> !parkour.isActive())
                .forEach(parkour -> player.sendMessage(
                        ChatColor.RED + "Id : " + ChatColor.WHITE + parkour.getId() + ChatColor.RED + " Name : " + ChatColor.WHITE + parkour.getName()
                        )
                );

        player.sendMessage(ChatColor.GREEN + "--------------------------");
    }

    @Override
    public String getName() {
        return NAME;
    }
}