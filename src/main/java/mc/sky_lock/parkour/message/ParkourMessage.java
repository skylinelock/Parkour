package mc.sky_lock.parkour.message;

import org.bukkit.ChatColor;

/**
 * @author sky_lock
 */

public enum ParkourMessage {

    ALREADY_EXISTS(ChatColor.RED + "The Parkour already exists"),
    NOT_FOUND(ChatColor.RED + "The Parkour is not found"),
    NOT_ENOUGH_ELEMENTS(ChatColor.RED + "Not enough Parkour elements");

    private final String text;

    ParkourMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
