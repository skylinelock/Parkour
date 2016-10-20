package mc.sky_lock.parkour.listener;

import mc.sky_lock.parkour.ParkourManager;
import mc.sky_lock.parkour.ParkourObj;
import mc.sky_lock.parkour.ParkourPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author sky_lock
 */
public class MoveListener implements Listener {
    private final ParkourManager manager;
    
    public MoveListener(ParkourManager manager) {
        this.manager = manager;
    }
    
    @EventHandler
    public void moveListener(PlayerMoveEvent event) {
        Location toLoc = event.getTo();
        Location fromLoc = event.getFrom();
        if (toLoc.getBlockX() == fromLoc.getBlockX() && toLoc.getBlockY() == fromLoc.getBlockY() && toLoc.getBlockZ() == fromLoc.getBlockZ()) {
            return;
        }
        
        Block underBlock = toLoc.getBlock();
        if (underBlock.getType() != Material.IRON_PLATE && underBlock.getType() != Material.GOLD_PLATE) {
            return;
        }
        Player player = event.getPlayer();
        ParkourPlayer pPlayer = new ParkourPlayer(player, new ParkourObj("Fuck"));
        
        manager.start(pPlayer);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(manager.stop(pPlayer) + "");
            }
        }.runTaskLater(manager.getPlugin(), 20L);

        
    }
}
