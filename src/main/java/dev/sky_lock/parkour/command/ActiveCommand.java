package dev.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.sky_lock.parkour.Optionals;
import dev.sky_lock.parkour.ParkourPlugin;
import dev.sky_lock.parkour.api.Parkour;
import dev.sky_lock.parkour.api.ParkourManager;
import dev.sky_lock.parkour.message.ParkourMessage;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
class ActiveCommand extends BaseCommand {
    private final ParkourPlugin plugin = ParkourPlugin.getInstance();

    @Subcommand("active")
    @CommandPermission("parkour.command.active")
    public void onCommand(Player player, String id) {
        ParkourManager parkourManager = plugin.getParkourManager();
        Optionals.ifPresentOrElse(parkourManager.getParkour(id), parkour -> {
            if (!checkAllElements(parkour)) {
                player.sendMessage(ParkourMessage.NOT_ENOUGH_ELEMENTS.getText());
                return;
            }
            parkour.setActive(true);
        }, () -> player.sendMessage(ParkourMessage.NOT_FOUND.getText()));
    }

    private boolean checkAllElements(Parkour parkour) {
        return parkour.getStartPoint() != null && parkour.getEndPoint() != null && parkour.getPresetPoint() != null && parkour.getName() != null;
    }
}