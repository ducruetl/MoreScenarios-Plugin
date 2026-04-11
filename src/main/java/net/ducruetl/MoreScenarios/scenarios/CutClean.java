package net.ducruetl.MoreScenarios.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.ducruetl.MoreScenarios.Scenario;

public class CutClean implements Scenario, Listener {
    private boolean enabled = false;

    @Override
    public String getName() {
        return "CutClean";
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
    public void onBlockBreak(BlockBreakEvent event) {

        if (!enabled) return;

        Material type = event.getBlock().getType();

        if (type == Material.IRON_ORE) {

            event.setCancelled(true);

            event.getBlock().setType(Material.AIR);

            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(),
                    new ItemStack(Material.IRON_INGOT)
            );
        }

        if (type == Material.GOLD_ORE) {

            event.setCancelled(true);

            event.getBlock().setType(Material.AIR);

            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(),
                    new ItemStack(Material.GOLD_INGOT)
            );
        }
    }
}
