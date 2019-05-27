package dev.sky_lock.parkour.api;

import dev.sky_lock.parkour.Utils;
import org.apache.commons.lang.time.StopWatch;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class Runner {

    private final Player player;
    private final Parkour parkour;
    private final StopWatch stopWatch;

    /**
     * 引数として渡されたPlayerとParkourを紐づけるParkourPlayerインスタンスを作成します。
     *
     * @param player Player
     * @param parkour Parkour
     */
    public Runner(Player player, Parkour parkour) {
        this.player = player;
        this.parkour = parkour;
        stopWatch = new StopWatch();
        stopWatch.start();

    }

    public void sendStartContents() {
        player.sendMessage("");
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + parkour.getName() + " Parkour challenge started!");
        player.sendMessage("");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
    }

    public void sendEndContents(long timeMillis) {
        String time = Utils.durationFormat(timeMillis);
        player.sendMessage("");
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + parkour.getName() + " Parkour challenge succeeded!");
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.GREEN + "Total Time: " + time);
        player.sendMessage("");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
    }

    public void sendFailContents() {
        player.sendMessage(ChatColor.DARK_AQUA + "Parkour" + ChatColor.DARK_GRAY + "≫ " + ChatColor.RED + parkour.getName() + " Parkour challenge failed!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 0.0F);
    }

    /**
     * 現在の計測タイムをミリ秒で返します。
     *
     * @return ミリ秒で表される現在の計測タイム
     */
    public long getTime() {
        return stopWatch.getTime();
    }

    /**
     * 紐づけられたPlayerを返します。
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 紐づけられたParkourを返します。
     *
     * @return Parkour
     */
    public Parkour getParkour() {
        return parkour;
    }

}
