package mc.sky_lock.parkour.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Single;
import co.aikar.commands.annotation.Subcommand;
import mc.sky_lock.parkour.ParkourPlugin;
import org.apache.commons.lang.BooleanUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

@CommandAlias("%parkour")
public class ConfigCommand extends BaseCommand {
    private final FileConfiguration config = ParkourPlugin.getInstance().getConfig();

    @Subcommand("config")
    @CommandCompletion("falldamage|voiddamage|tospawn|fall|void true|false")
    public void onCommand(Player player, @Single String arg1, @Single String arg2) {
        switch (arg1.toLowerCase()) {
            case "falldamage":
            case "fall":
                config.set("cancelDamage.fall", !toBoolean(arg2));
                player.sendMessage(ChatColor.GREEN + "Set fall-damage to " + toBoolean(arg2));
                return;
            case "voiddamage":
            case "void":
                config.set("cancelDamage.void", !toBoolean(arg2));
                player.sendMessage(ChatColor.GREEN + "Set void-damage to " + toBoolean(arg2));
                return;
            case "tospawn":
                config.set("respawn.tospawn", !toBoolean(arg2));
                player.sendMessage(ChatColor.GREEN + "Set to-respawn to " + toBoolean(arg2));
        }
    }

    private boolean toBoolean(String string) {
        return BooleanUtils.toBoolean(string);
    }
}
