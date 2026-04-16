package net.ducruetl.MoreScenarios;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.ducruetl.MoreScenarios.scenarios.BowSwap;
import net.ducruetl.MoreScenarios.scenarios.CutClean;
import net.ducruetl.MoreScenarios.scenarios.HasteyBabies;
import net.ducruetl.MoreScenarios.scenarios.HasteyBoys;
import net.ducruetl.MoreScenarios.scenarios.HealthAboveHead;

public class MoreScenarios extends JavaPlugin {

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
