package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.message.CommandUsage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

class UsageCommand implements ICommand {

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            player.sendMessage(CommandUsage.LIST.getText());
            player.sendMessage(CommandUsage.INFO.getText());
            player.sendMessage(CommandUsage.ADD.getText());
            player.sendMessage(CommandUsage.SET_NAME.getText());
            player.sendMessage(CommandUsage.SET_START.getText());
            player.sendMessage(CommandUsage.SET_END.getText());
            player.sendMessage(CommandUsage.SET_PRE.getText());
            player.sendMessage(CommandUsage.ACTIVE.getText());
            player.sendMessage(CommandUsage.REMOVE.getText());
            player.sendMessage(CommandUsage.RELOAD.getText());
            return;
        }
        sender.sendMessage(CommandUsage.RELOAD.getText());
    }
}
