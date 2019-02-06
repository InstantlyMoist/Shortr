package me.kyllian.shortr.listeners;

import me.kyllian.shortr.ShortrPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.MalformedURLException;
import java.net.URL;

public class PlayerChatListener implements Listener {

    private ShortrPlugin plugin;

    public PlayerChatListener(ShortrPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("shortr.chat")) return;
        String[] split = event.getMessage().split(" ");
        for (String string : split) {
            if (isURL(string)) {
                String finalUrl = plugin.getUrlHandler().shortUrl(string);
                event.setMessage(event.getMessage().replace(string, finalUrl));
            }
        }
    }

    public boolean isURL(String link) {
        try {
            URL url = new URL(link);
            return true;
        } catch (MalformedURLException exception) {
            return false;
        }
    }
}
