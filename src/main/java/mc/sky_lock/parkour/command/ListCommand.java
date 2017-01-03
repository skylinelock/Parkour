package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.Parkour;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author sky_lock
 */

class ListCommand implements ICommand, ConsoleCancellable {

    private final ParkourHandler handler;

    ListCommand(@NotNull ParkourHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("parkour.command.list")) {
            player.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        List<Parkour> parkours = handler.getParkours();

        player.sendMessage(ChatColor.GREEN + "------  [" + ChatColor.WHITE + "Parkour List" + ChatColor.GREEN + "]  ------");

        for (Parkour parkour : parkours) {
            if (parkour.isActive()) {
                player.sendMessage(
                        ChatColor.GREEN + "Id : " + ChatColor.WHITE + parkour.getId() + ChatColor.GREEN + " Name : " + ChatColor.WHITE + parkour.getName()
                );
                continue;
            }
            player.sendMessage(
                    ChatColor.RED + "Id : " + ChatColor.WHITE + parkour.getId() + ChatColor.RED + " Name : " + ChatColor.WHITE + parkour.getName()
            );

        }
        player.sendMessage(ChatColor.GREEN + "--------------------------");
    }
}
