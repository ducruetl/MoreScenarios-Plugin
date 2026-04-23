package net.ducruetl.UHCAndScenarios.scenarios;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.ducruetl.UHCAndScenarios.Scenario;

public class BowSwap implements Scenario, Listener {
    private boolean enabled = false;

    @Override
    public String getName() {
        return "BowSwap";
    }

    @Override
    public ArrayList<String> getIncompatibleScenarios() {
        return new ArrayList<>();
    }

    @Override
    public void enable(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        enabled = true;
    }

    @Override
    public void disable() {
        HandlerList.unregisterAll(this);

        enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @EventHandler
    public void onArrowTouched(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player 
        && event.getDamager() instanceof Arrow) {
            Player player = (Player) event.getEntity();
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player otherPlayer = (Player) arrow.getShooter();
                Location pos1 = player.getLocation();
                Location pos2 = otherPlayer.getLocation();

                player.teleport(pos2);
                otherPlayer.teleport(pos1);
            }
        }
    }
    
}
