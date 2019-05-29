package dev.sky_lock.parkour;

import lombok.Getter;
import org.bukkit.Material;

/**
 * @author sky_lock
 */

public enum PressurePlate {

    HEAVY(Material.HEAVY_WEIGHTED_PRESSURE_PLATE),
    LIGHT(Material.LIGHT_WEIGHTED_PRESSURE_PLATE),
    ACACIA(Material.ACACIA_PRESSURE_PLATE),
    OAK(Material.OAK_PRESSURE_PLATE),
    DARK_OAK(Material.DARK_OAK_PRESSURE_PLATE),
    BIRCH(Material.BIRCH_PRESSURE_PLATE),
    JUNGLE(Material.JUNGLE_PRESSURE_PLATE),
    SPRUCE(Material.SPRUCE_PRESSURE_PLATE),
    STONE(Material.STONE_PRESSURE_PLATE);

    @Getter
    private final Material material;

    PressurePlate(Material material) {
        this.material = material;
    }
}
