package mc.sky_lock.parkour.message;

import org.bukkit.ChatColor;

/**
 * @author sky_lock
 */

public enum CommandUsage {

    RELOAD(ChatColor.GREEN + "/parkour reload"),
    ACTIVE(ChatColor.GREEN + "/parkour active [id]"),
    LOCK(ChatColor.GREEN + "/parkour lock [id]"),
    ADD(ChatColor.GREEN + "/parkour add [id]"),
    REMOVE(ChatColor.GREEN + "/parkour remove [id]"),
    LIST(ChatColor.GREEN + "/parkour list"),
    INFO(ChatColor.GREEN + "/parkour info [id]"),
    SET_NAME(ChatColor.GREEN + "/parkour setname [id] [name]"),
    SET_START(ChatColor.GREEN + "/parkour setstart [id]"),
    SET_END(ChatColor.GREEN + "/parkour setend [id]"),
    SET_PRE(ChatColor.GREEN + "/parkour setpre [id]"),
    SAVE(ChatColor.GREEN + "/parkour save [id]");

    private final String text;

    private CommandUsage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
