package net.ducruetl.UHCAndScenarios;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandListener implements CommandExecutor {
    private ScenarioManager manager;
    private JavaPlugin plugin;

    public CommandListener(ScenarioManager manager, JavaPlugin plugin) {
        this.manager = manager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            return false;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {

            case "enable": 
                if (args.length != 2) return false;

                Scenario scenario = manager.get(args[1].toLowerCase());

                if (scenario == null) {
                    sender.sendMessage("Unknown scenario.");
                    return true;
                }

                manager.enable(scenario.getName(), plugin, sender);
                break;

            case "disable": 
                if (args.length != 2) return false;

                scenario = manager.get(args[1].toLowerCase());

                if (scenario == null) {
                    sender.sendMessage("Unknown scenario.");
                    return true;
                }

                manager.disable(scenario.getName(), sender);
                break;

            case "list": 
                sender.sendMessage("Scenarios:");

                for (Scenario s : manager.getAll()) {
                    sender.sendMessage("- " + s.getName() + (s.isEnabled() ? " (on)" : " (off)"));
                }
                break;

            default: 
                return false;
        }

        return true;
    }
}
