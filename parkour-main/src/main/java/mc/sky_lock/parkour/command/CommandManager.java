package mc.sky_lock.parkour.command;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.CommandReplacements;
import mc.sky_lock.parkour.ParkourPlugin;

/**
 * @author sky_lock
 */

public class CommandManager {

    private final BukkitCommandManager commandManager = new BukkitCommandManager(ParkourPlugin.getInstance());

    public void register() {
        CommandReplacements replacements = commandManager.getCommandReplacements();
        replacements.addReplacement("mc/sky_lock/parkour", "parkour|pk");

        commandManager.registerCommand(new ParkourCommand());
        commandManager.registerCommand(new ActiveCommand());
        commandManager.registerCommand(new AddCommand());
        commandManager.registerCommand(new DeleteCommand());
        commandManager.registerCommand(new InfoCommand());
        commandManager.registerCommand(new ListCommand());
        commandManager.registerCommand(new LockCommand());
        commandManager.registerCommand(new RecordCommand());
        commandManager.registerCommand(new ReloadCommand());
        commandManager.registerCommand(new SaveCommand());
        commandManager.registerCommand(new SetEndCommand());
        commandManager.registerCommand(new SetPreCommand());
        commandManager.registerCommand(new SetStartCommand());
        commandManager.registerCommand(new SetNameCommand());
        commandManager.registerCommand(new TeleportCommand());
        commandManager.registerCommand(new SetHeightCommand());
        commandManager.registerCommand(new ConfigCommand());
    }
}
