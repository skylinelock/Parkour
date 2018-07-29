package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import mc.sky_lock.parkour.message.CommandUsage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
public class ParkourCommand extends BaseCommand {

    @Default
    public void onCommand(CommandSender sender) {
        sender.sendMessage(CommandUsage.RELOAD.getText());
        if (sender instanceof Player) {
            sender.sendMessage(CommandUsage.LIST.getText());
            sender.sendMessage(CommandUsage.INFO.getText());
            sender.sendMessage(CommandUsage.ADD.getText());
            sender.sendMessage(CommandUsage.SET_NAME.getText());
            sender.sendMessage(CommandUsage.SET_START.getText());
            sender.sendMessage(CommandUsage.SET_END.getText());
            sender.sendMessage(CommandUsage.SET_PRE.getText());
            sender.sendMessage(CommandUsage.ACTIVE.getText());
            sender.sendMessage(CommandUsage.LOCK.getText());
            sender.sendMessage(CommandUsage.DELETE.getText());
            sender.sendMessage(CommandUsage.SAVE.getText());
            sender.sendMessage(CommandUsage.TELEPORT.getText());
        }
    }
}
