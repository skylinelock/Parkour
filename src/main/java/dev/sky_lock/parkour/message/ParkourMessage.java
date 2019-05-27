package dev.sky_lock.parkour.message;

import org.bukkit.ChatColor;

/**
 * @author sky_lock
 */

public enum ParkourMessage {

    ALREADY_EXISTS(ChatColor.RED + "Already exists"),
    NOT_FOUND(ChatColor.RED + "Not found"),
    NOT_ENOUGH_ELEMENTS(ChatColor.RED + "Not enough Parkour elements"),
    PRESET_NOT_FOUND(ChatColor.RED + "Preset Point was not found");

    private final String text;

    ParkourMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
