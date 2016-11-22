package mc.sky_lock.parkour.command.config;

/**
 * @author sky_lock
 */

public enum ConfigElement {

    RESPAWN_Y("respawnY");

    private final String element;

    private ConfigElement(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }
}
