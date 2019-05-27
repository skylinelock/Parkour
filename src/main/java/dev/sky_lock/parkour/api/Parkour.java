package dev.sky_lock.parkour.api;

import org.bukkit.Location;

/**
 * @author sky_lock
 */

public class Parkour {

    private final String id;
    private String name;
    private boolean active;
    private boolean save;
    private Location startPoint;
    private Location endPoint;
    private Location presetPoint;

    /**
     * 引数として渡された文字列IDを持つParkourインスタンスを作成します。
     *
     * @param id 文字列ID
     */
    public Parkour(String id) {
        this.id = id;
    }

    /**
     * {@code null} ではない文字列IDを返します。
     *
     * @return ID文字列
     */
    public String getId() {
        return id;
    }

    /**
     * Parkourがアクティブ状態かどうかを返します。
     *
     * @return アクティブ状態であったら {@code true}
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Parkourがロック状態かどうかを返します。
     *
     * @return ロック状態であったら {@code true}
     */
    public boolean isLocked() {
        return !active;
    }

    /**
     * Parkourのアクティブ/ロック状態を設定します。
     *
     * @param active アクティブ状態は {@code true}
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Parkourの名称が存在していたら文字列、そうでなければ {@code null} を返します。
     *
     * @return 名称文字列
     */
    public String getName() {
        return name;
    }

    /**
     * Parkourの名称を設定します。
     *
     * @param name 名称文字列
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean canSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    /**
     * Parkourのスタート地点が存在していたらその地点のLocation、そうでなければ {@code null} を返します。
     *
     * @return スタート地点
     */
    public Location getStartPoint() {
        return startPoint;
    }

    /**
     * Parkourのスタート地点を設定します。
     *
     * @param startPoint スタート地点
     */
    public void setStartPoint(Location startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * Parkourのゴール地点が存在していたらその地点のLocation、そうでなければ {@code null} を返します。
     *
     * @return ゴール地点
     */
    public Location getEndPoint() {
        return endPoint;
    }

    /**
     * Parkourのゴール地点を設定します。
     *
     * @param endPoint ゴール地点
     */
    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Parkourのプリセット地点が存在していたらその地点のLocation、そうでなければ {@code null} を返します。
     *
     * @return プリセット地点
     */
    public Location getPresetPoint() {
        return presetPoint;
    }

    /**
     * Parkourのプリセット地点を設定します。
     *
     * @param presetPoint プリセット地点
     */
    public void setPresetPoint (Location presetPoint) {
        this.presetPoint = presetPoint;
    }

    /**
     * このParkourを表すIDとName、アクティブ状態を連結させたStringオブジェクトを返します。
     *
     * @return IDとName、アクティブ状態が連結された文字列
     */
    @Override
    public String toString() {
        return "Parkour [id=" + id + ", name=" + name + ", active=" + active + "]";
    }


}
