package net.ducruetl.MoreScenarios;

import org.bukkit.plugin.java.JavaPlugin;

public interface Scenario {
    String getName();

    void enable(JavaPlugin plugin);

    void disable();

    boolean isEnabled();
}
