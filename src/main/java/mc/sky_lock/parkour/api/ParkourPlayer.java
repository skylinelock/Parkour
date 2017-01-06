package mc.sky_lock.parkour.api;

import org.bukkit.entity.Player;

/**
 * @author sky_lock
 */

public class ParkourPlayer {

    private final Player player;
    private final Parkour parkour;
    private final long startTime;

    /**
     * 引数として渡されたPlayerとParkourを紐づけるParkourPlayerインスタンスを作成します。
     *
     * @param player Player
     * @param parkour Parkour
     */
    public ParkourPlayer(Player player, Parkour parkour) {
        this.player = player;
        this.parkour = parkour;

        this.startTime = System.currentTimeMillis();
    }

    /**
     * 現在の計測タイムをミリ秒で返します。
     *
     * @return ミリ秒で表される現在の計測タイム
     */
    public long getCurrentTimeMillis() {
        long currentTime = System.currentTimeMillis();
        return currentTime - startTime;
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
