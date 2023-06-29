package me.l2x9.data.platform;

public enum Platform {
    BUKKIT,
    VELOCITY;

    private static Platform platform;

    static {
        try {
            Class.forName("org.bukkit.plugin.java.JavaPlugin");
            platform = BUKKIT;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.velocitypowered.api.plugin.Plugin");
                platform = VELOCITY;
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("Could not find a supported platform", ex);
            }
        }
    }

    public static Platform get() {
        return platform;
    }
}
