package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourHandler;
import mc.sky_lock.parkour.api.ParkourManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 * @author sky_lock
 */

@SuppressWarnings("unused")
public class EntityListener implements Listener {
    private final ParkourHandler handler;

    public EntityListener(ParkourHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void entityDamage(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }

        FileConfiguration config = handler.getConfig();
        if (damageCause == EntityDamageEvent.DamageCause.VOID) {
            ParkourManager parkourManager = handler.getParkourManager();
            Player player = (Player) entity;
            if (parkourManager.isParkourPlayer(player)) {
                boolean damage = config.getBoolean("cancelDamage.void", true);
                event.setCancelled(damage);
            }
        } else if (damageCause == DamageCause.FALL) {
            boolean damage = config.getBoolean("cancelDamage.fall", true);
            event.setCancelled(damage);
        }
    }
}
