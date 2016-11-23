package mc.sky_lock.parkour.config;

/**
 * @author sky_lock
 */

public enum ConfigElement {

    TELEPORT_HEIGHT("teleportHeight");

    private final String element;

    ConfigElement(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }
}
