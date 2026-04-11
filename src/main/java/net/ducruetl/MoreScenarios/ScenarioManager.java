package net.ducruetl.MoreScenarios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

public class ScenarioManager {

    private final Map<String, Scenario> scenarios = new HashMap<>();

    public void register(Scenario scenario) {
        scenarios.put(scenario.getName().toLowerCase(), scenario);
    }

    public void enable(String name, JavaPlugin plugin) {
        Scenario scenario = scenarios.get(name.toLowerCase());

        if (scenario != null && !scenario.isEnabled()) {
            scenario.enable(plugin);
        }
    }

    public void disable(String name) {
        Scenario scenario = scenarios.get(name.toLowerCase());

        if (scenario != null && scenario.isEnabled()) {
            scenario.disable();
        }
    }

    public boolean isEnabled(String name) {
        Scenario scenario = scenarios.get(name.toLowerCase());
        return scenario != null && scenario.isEnabled();
    }

    public Collection<Scenario> getAll() {
        return scenarios.values();
    }

    public Scenario get(String name) {
        return scenarios.get(name);
    }
}
