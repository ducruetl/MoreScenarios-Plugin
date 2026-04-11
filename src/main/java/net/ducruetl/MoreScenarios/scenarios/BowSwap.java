package net.ducruetl.MoreScenarios.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.ducruetl.MoreScenarios.Scenario;

public class BowSwap implements Scenario, Listener {
    private boolean enabled = false;

    @Override
    public String getName() {
        return "BowSwap";
    }

    @Override
    public void enable(JavaPlugin plugin) {
        enabled = true;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void disable() {
        enabled = false;
        HandlerList.unregisterAll(this);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @EventHandler
    public void onArrowTouched(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player 
        && event.getDamager() instanceof Arrow arrow
        && arrow.getShooter() instanceof Player otherPlayer) {
            Location pos1 = player.getLocation();
            Location pos2 = otherPlayer.getLocation();

            player.teleport(pos2);
            otherPlayer.teleport(pos1);
        }
    }
    
}
