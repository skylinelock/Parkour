package mc.sky_lock.parkour.message;

import org.bukkit.ChatColor;

/**
 * @author sky_lock
 */

public enum FailedMessage {

    DONT_HAVE_PERM(ChatColor.RED + "You don't enough have permissions to do this"),
    NOT_PLAYER(ChatColor.RED + "You must be a player to use this command"),
    NOT_ENOUGH_ARGS(ChatColor.RED + "Not enough arguments"),
    ID_EXISTS(ChatColor.RED + "The ID already exists"),
    ACTIVE(ChatColor.RED + "Active failed"),
    LOCK(ChatColor.RED + "Lock failed"),
    INFO(ChatColor.RED + "Info failed"),
    REMOVE(ChatColor.RED + "Remove failed"),
    RELOAD(ChatColor.RED + "Reload failed"),
    SET_NAME(ChatColor.RED + "Set name failed"),
    SET_START(ChatColor.RED + "Set startpoint failed"),
    SET_END(ChatColor.RED + "Set endpoint failed"),
    SET_PRE(ChatColor.RED + "Set prepoint failed"),
    TELEPORT(ChatColor.RED + "Teleport failed"),
    SAVE(ChatColor.RED + "Turn save failed");

    private final String text;

    FailedMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
