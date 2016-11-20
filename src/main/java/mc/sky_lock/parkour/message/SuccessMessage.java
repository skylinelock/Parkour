package mc.sky_lock.parkour.message;

import org.bukkit.ChatColor;

/**
 * @author sky_lock
 */

public enum SuccessMessage {

    RELOAD(ChatColor.GREEN + "Reload successful"),
    ACTIVE(ChatColor.GREEN + "Turn active successful"),
    ADD(ChatColor.GREEN + "Add parkour successful"),
    INFO(ChatColor.GREEN + "Info succeeded"),
    REMOVE(ChatColor.GREEN + "Remove successful"),
    SET_NAME(ChatColor.GREEN + "Set name successful"),
    SET_PRE(ChatColor.GREEN + "Set pre successful"),
    SET_START(ChatColor.GREEN + "Set start successful"),
    SET_END(ChatColor.GREEN + "Set end successful"),
    TELEPORT(ChatColor.GREEN + "Teleport successful");

    private final String text;

    private SuccessMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
