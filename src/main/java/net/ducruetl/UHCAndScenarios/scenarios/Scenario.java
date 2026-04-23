package net.ducruetl.UHCAndScenarios.scenarios;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

public interface Scenario {
    String getName();

    void enable(JavaPlugin plugin);

    void disable();

    boolean isEnabled();

    ArrayList<String> getIncompatibleScenarios();
}
