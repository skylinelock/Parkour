package dev.sky_lock.parkour;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

/**
 * @author sky_lock
 */

public class PluginLogger {

    private final ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
    private final static String PREFIX = ChatColor.RED + "[" + ChatColor.GREEN + "Parkour" + ChatColor.RED + "] ";

    public void out(String text) {
        consoleSender.sendMessage(PREFIX + text);
    }
}
