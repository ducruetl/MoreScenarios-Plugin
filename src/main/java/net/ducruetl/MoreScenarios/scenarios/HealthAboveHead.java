package net.ducruetl.MoreScenarios.scenarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import net.ducruetl.MoreScenarios.Scenario;

public class HealthAboveHead implements Scenario, Listener {
    private boolean enabled = false;

    private JavaPlugin plugin;
    
    private BukkitTask task;

    private final Map<UUID, ArmorStand> stands = new HashMap<UUID, ArmorStand>();

    @Override
    public String getName() {
        return "HealthAboveHead";
    }

    @Override
    public void enable(JavaPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);

        for (Player player : Bukkit.getOnlinePlayers()) {
            createStand(player);
        }

        task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ArmorStand stand = stands.get(player.getUniqueId());

                    if (stand == null || stand.isDead()) {
                        createStand(player);
                        stand = stands.get(player.getUniqueId());
                    }

                    if (stand == null) continue;

                    Location loc = player.getLocation().clone().add(0.0, 0.8, 0.0);
                    stand.teleport(loc);
                }
            }
        }, 1L, 1L);

        enabled = true;
    }

    @Override
    public void disable() {
        if (task != null) {
            task.cancel();
            task = null;
        }

        HandlerList.unregisterAll(this);

        for (ArmorStand stand : stands.values()) {
            if (stand != null && !stand.isDead()) {
                stand.remove();
            }
        }
        stands.clear();

        enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public ArrayList<String> getIncompatibleScenarios() {
        return new ArrayList<>();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        removeStand(event.getPlayer());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                @Override
                public void run() {
                    updateStand(player);
                }
            });
        }
    }

    @EventHandler
    public void onHeal(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                @Override
                public void run() {
                    updateStand(player);
                }
            });
        }
    }

    private void createStand(Player player) {
        removeStand(player);

        Location loc = player.getLocation().clone().add(0.0, 0.8, 0.0);
        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);

        stand.setVisible(false);
        stand.setGravity(false);
        stand.setSmall(true);
        stand.setBasePlate(false);
        stand.setCustomNameVisible(true);

        // Reduce hitbox interaction
        stand.setMarker(false);

        stands.put(player.getUniqueId(), stand);
        updateStand(player);
    }

    private void removeStand(Player player) {
        ArmorStand old = stands.remove(player.getUniqueId());
        if (old != null && !old.isDead()) {
            old.remove();
        }
    }

    private void updateStand(Player player) {
        ArmorStand stand = stands.get(player.getUniqueId());
        if (stand == null || stand.isDead()) return;

        double health = player.getHealth();
        double maxHealth = player.getMaxHealth();

        String color;
        double ratio = health / maxHealth;

        if (ratio > 0.66D) {
            color = ChatColor.GREEN + "";
        } else if (ratio > 0.33D) {
            color = ChatColor.YELLOW + "";
        } else {
            color = ChatColor.RED + "";
        }

        int hp = (int) Math.ceil(health);

        stand.setCustomName(color + hp + ChatColor.RED + " ❤");
    }
    
}
