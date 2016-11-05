package mc.sky_lock.parkour.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public interface ICommand {

    public void execute(Player player, Command command, String label, String args[]);

}
