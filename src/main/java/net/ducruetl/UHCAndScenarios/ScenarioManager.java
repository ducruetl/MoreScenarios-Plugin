package net.ducruetl.UHCAndScenarios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ScenarioManager {

    private final Map<String, Scenario> scenarios = new HashMap<>();

    public void register(Scenario scenario) {
        scenarios.put(scenario.getName().toLowerCase(), scenario);
    }

    public void enable(String name, JavaPlugin plugin, CommandSender sender) {
        Scenario scenario = scenarios.get(name.toLowerCase());

        if (scenario != null && !scenario.isEnabled()) {

            // Check if there is a incompatible scenario enabled
            for (String scenarioName : scenario.getIncompatibleScenarios()) {
                if (!scenarios.containsKey(scenarioName.toLowerCase())) {
                    continue;
                }

                if (scenarios.get(scenarioName.toLowerCase()).isEnabled()) {
                    sender.sendMessage(ChatColor.RED + "This scenario can't be enabled because an incompatible scenario is already enabled (" + scenarioName + ")");
                    return;
                }
            }

            scenario.enable(plugin);
            sender.sendMessage(ChatColor.GREEN + "Enabled: " + scenario.getName());
        }
    }

    public void disable(String name, CommandSender sender) {
        Scenario scenario = scenarios.get(name.toLowerCase());

        if (scenario != null && scenario.isEnabled()) {
            scenario.disable();
            sender.sendMessage(ChatColor.GREEN + "Disabled: " + scenario.getName());
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
