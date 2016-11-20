package mc.sky_lock.parkour.message;

import org.bukkit.ChatColor;

/**
 * @author sky_lock
 */

public enum FailedMessage {

    NOT_PLAYER(ChatColor.RED + "You must be a player to use this command"),
    NOT_ENOUGH_ARGS(ChatColor.RED + "Not enough arguments"),
    ID_EXISTS(ChatColor.RED + "The ID already exists"),
    ACTIVE(ChatColor.RED + "Set active failed"),
    INFO(ChatColor.RED + "Info failed"),
    REMOVE(ChatColor.RED + "Remove failed"),
    SET_NAME(ChatColor.RED + "Set name failed"),
    SET_START(ChatColor.RED + "Set start failed"),
    SET_END(ChatColor.RED + "Set end failed"),
    SET_PRE(ChatColor.RED + "Set pre failed"),
    TELEPORT(ChatColor.RED + "Teleport failed");


    private final String text;

    private FailedMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
