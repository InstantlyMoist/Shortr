package me.kyllian.shortr.utils;

import me.kyllian.shortr.ShortrPlugin;
import org.bukkit.ChatColor;

public class MessageHandler {

    private ShortrPlugin plugin;

    public MessageHandler(ShortrPlugin plugin) {
        this.plugin = plugin;
    }

    public String colorTranslate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getNoPermissionMessage() {
        return colorTranslate(plugin.getConfig().getString("PluginMessages.NoPermission"));
    }

    public String getReloadedMessage() {
        return colorTranslate(plugin.getConfig().getString("PluginMessages.Reloaded"));
    }

    public String getInvalidArgumentMessage() {
        return colorTranslate(plugin.getConfig().getString("PluginMessages.InvalidArgument"));
    }
}
