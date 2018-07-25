package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("parkour|pk")
public class SettingCommand extends BaseCommand {

    @Subcommand("setting")
    public void onCommand(Player player, String key, @Optional boolean bool) {

    }
}
