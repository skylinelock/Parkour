package mc.sky_lock.parkour.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author sky_lock
 */

public interface ICommand {

    String getName();
    void execute(CommandSender sender, Command command, String label, String args[]);

}