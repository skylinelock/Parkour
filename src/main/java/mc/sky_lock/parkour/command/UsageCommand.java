package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.message.CommandUsage;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

class UsageCommand implements ICommand {

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("parkour.command.usage")) {
            sender.sendMessage(FailedMessage.DONT_HAVE_PERM.getText());
            return;
        }
        sender.sendMessage(CommandUsage.RELOAD.getText());
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(CommandUsage.LIST.getText());
            player.sendMessage(CommandUsage.INFO.getText());
            player.sendMessage(CommandUsage.ADD.getText());
            player.sendMessage(CommandUsage.SET_NAME.getText());
            player.sendMessage(CommandUsage.SET_START.getText());
            player.sendMessage(CommandUsage.SET_END.getText());
            player.sendMessage(CommandUsage.SET_PRE.getText());
            player.sendMessage(CommandUsage.ACTIVE.getText());
            player.sendMessage(CommandUsage.LOCK.getText());
            player.sendMessage(CommandUsage.DELETE.getText());
            player.sendMessage(CommandUsage.SAVE.getText());
            player.sendMessage(CommandUsage.TELEPORT.getText());
        }
    }
}