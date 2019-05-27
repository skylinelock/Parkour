package dev.sky_lock.parkour.message;

import org.bukkit.ChatColor;

/**
 * @author sky_lock
 */

public enum FailedMessage {

    DONT_HAVE_PERM(ChatColor.RED + "You don't enough have permissions to do this"),
    NOT_PLAYER(ChatColor.RED + "You must be a player to use this command"),
    NOT_ENOUGH_ARGS(ChatColor.RED + "Not enough arguments"),
    RELOAD(ChatColor.RED + "Reload failed");

    private final String text;

    FailedMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
