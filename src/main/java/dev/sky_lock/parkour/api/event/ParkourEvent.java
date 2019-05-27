package dev.sky_lock.parkour.api.event;

import dev.sky_lock.parkour.api.Parkour;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * @author sky_lock
 */

public class ParkourEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Parkour parkour;
    private boolean cancelled = false;

    public ParkourEvent(Player player, Parkour parkour) {
        super(player);
        this.parkour = parkour;
    }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Parkour getParkour() {
        return parkour;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
