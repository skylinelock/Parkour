package mc.sky_lock.parkour.command;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.message.CommandUsage;
import mc.sky_lock.parkour.message.FailedMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class CommandHandler implements CommandExecutor {

    private final ParkourHandler handler;

    public CommandHandler(ParkourHandler handler) {
        this.handler = handler;
        handler.putSubCommand(new ActiveCommand(handler));
        handler.putSubCommand(new AddCommand(handler));
        handler.putSubCommand(new DeleteCommand(handler));
        handler.putSubCommand(new InfoCommand(handler));
        handler.putSubCommand(new ListCommand(handler));
        handler.putSubCommand(new LockCommand(handler));
        handler.putSubCommand(new ReloadCommand(handler));
        handler.putSubCommand(new SaveCommand(handler));
        handler.putSubCommand(new SetEndCommand(handler));
        handler.putSubCommand(new SetNameCommand(handler));
        handler.putSubCommand(new SetPreCommand(handler));
        handler.putSubCommand(new SetStartCommand(handler));
        handler.putSubCommand(new TeleportCommand(handler));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sendUsage(sender);
            return true;
        }
        ICommand subCmd = handler.getSubCommand(args[0].toLowerCase());
        if (subCmd == null) {
            sendUsage(sender);
            return true;
        }
        if (subCmd instanceof ConsoleCancellable && !(sender instanceof Player)) {
            sender.sendMessage(FailedMessage.NOT_PLAYER.getText());
            return true;
        }
        subCmd.execute(sender, command, label, args);
        return true;
    }

    private void sendUsage(CommandSender sender) {
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