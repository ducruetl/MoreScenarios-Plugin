package net.ducruetl.UHCAndScenarios;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.ducruetl.UHCAndScenarios.scenarios.BowSwap;
import net.ducruetl.UHCAndScenarios.scenarios.CutClean;
import net.ducruetl.UHCAndScenarios.scenarios.HasteyBabies;
import net.ducruetl.UHCAndScenarios.scenarios.HasteyBoys;
import net.ducruetl.UHCAndScenarios.scenarios.HealthAboveHead;

public class UHCAndScenarios extends JavaPlugin {

    private ScenarioManager scenarioManager;
    
    @Override
    public void onEnable() {
        scenarioManager = new ScenarioManager();

        scenarioManager.register(new CutClean());
        scenarioManager.register(new BowSwap());
        scenarioManager.register(new HasteyBoys());
        scenarioManager.register(new HasteyBabies());
        scenarioManager.register(new HealthAboveHead());

        getCommand("scenario").setExecutor(new CommandListener(scenarioManager, this));

        Bukkit.getLogger().log(Level.INFO, "Started " + getName());
    }

    @Override
    public void onDisable() {
        super.onDisable();

        Bukkit.getLogger().log(Level.INFO, "Stopped " + getName());
    }

}
