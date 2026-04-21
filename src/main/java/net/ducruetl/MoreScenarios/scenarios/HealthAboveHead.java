package net.ducruetl.MoreScenarios.scenarios;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.ducruetl.MoreScenarios.Scenario;

public class HealthAboveHead implements Scenario, Listener {
    private JavaPlugin plugin;

    private Scoreboard board;

    private Objective healthObjective;

    private boolean enabled = false;

    @Override
    public String getName() {
        return "HealthAboveHead";
    }

    @Override
    public void enable(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();

        Objective existing = board.getObjective("health");
        if (existing != null) {
            existing.unregister();
        }

        healthObjective = board.registerNewObjective("health", "dummy");
        healthObjective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        healthObjective.setDisplayName(ChatColor.RED + "❤");

        applyToAll();

        enabled = true;
    }

    @Override
    public void disable() {
        HandlerList.unregisterAll(this);
        removeFromAll();
        healthObjective.unregister();

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

    public void applyToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            apply(player);
        }
    }

    public void removeFromAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            remove(player);
        }
    }

    public void apply(Player player) {
        player.setScoreboard(board);
        update(player);
    }

    public void update(Player player) {
        int hp = (int) Math.ceil(player.getHealth());
        healthObjective.getScore(player.getName()).setScore(hp);
    }

    public void remove(Player player) {
        board.resetScores(player.getName());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        apply(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        remove(event.getPlayer());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                @Override
                public void run() {
                    update(player);
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
                    update(player);
                }
            });
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer() instanceof Player) {
            final Player player = event.getPlayer();
            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                @Override
                public void run() {
                    update(player);
                }
            });
        }
    }
}
