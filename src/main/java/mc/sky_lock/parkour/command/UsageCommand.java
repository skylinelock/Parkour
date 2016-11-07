package mc.sky_lock.parkour.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

class UsageCommand implements ICommand {

    @Override
    public void execute(Player player, Command command, String label, String[] args) {
        player.sendMessage(ChatColor.GREEN + "/parkour list");
        player.sendMessage(ChatColor.GREEN + "/parkour info [id]");
        player.sendMessage(ChatColor.GREEN + "/parkour add [id]");
        player.sendMessage(ChatColor.GREEN + "/parkour setname [id] [name]");
        player.sendMessage(ChatColor.GREEN + "/parkour setstart [id]");
        player.sendMessage(ChatColor.GREEN + "/parkour setend [id]");
        player.sendMessage(ChatColor.GREEN + "/parkour setpre [id]");
        player.sendMessage(ChatColor.GREEN + "/parkour active [id]");
        player.sendMessage(ChatColor.GREEN + "/parkour remove [id]");
    }
}
