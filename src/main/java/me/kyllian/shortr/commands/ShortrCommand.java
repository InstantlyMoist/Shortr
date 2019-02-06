package me.kyllian.shortr.commands;

import me.kyllian.shortr.ShortrPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShortrCommand implements CommandExecutor {

    private ShortrPlugin plugin;

    public ShortrCommand(ShortrPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("shortr.reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(plugin.getMessageHandler().getReloadedMessage());
                    return true;
                }
                sender.sendMessage(plugin.getMessageHandler().getNoPermissionMessage());
                return true;
            }
        }
        sender.sendMessage(plugin.getMessageHandler().getInvalidArgumentMessage());
        return true;
    }
}
