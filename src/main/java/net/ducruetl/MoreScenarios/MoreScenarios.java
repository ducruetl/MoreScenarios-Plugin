package net.ducruetl.MoreScenarios;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreScenarios extends JavaPlugin {
    
    @Override
    public void onEnable() {
        super.onEnable();

        Bukkit.getLogger().log(Level.INFO, "Started " + getName());
    }

    @Override
    public void onDisable() {
        super.onDisable();

        Bukkit.getLogger().log(Level.INFO, "Stopped " + getName());
    }

}
