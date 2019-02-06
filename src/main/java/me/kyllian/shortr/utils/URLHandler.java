package me.kyllian.shortr.utils;

import me.kyllian.shortr.ShortrPlugin;
import net.swisstech.bitly.builder.v3.ShortenRequest;
import org.bukkit.Bukkit;

public class URLHandler {

    private ShortrPlugin plugin;

    public URLHandler(ShortrPlugin plugin) {
        this.plugin = plugin;
    }

    public String shortUrl(String link) {
        ShortenRequest shortenRequest = new ShortenRequest(plugin.getConfig().getString("Token"));
        if (shortenRequest.call().status_txt.equalsIgnoreCase("INVALID_ARG_ACCESS_TOKEN")) {
            Bukkit.broadcastMessage(plugin.getMessageHandler().colorTranslate("&8&oShortr: &7Please enter a valid token in the config.yml"));
            return link;
        }
        try {
            String url = shortenRequest.setLongUrl(link).call().data.url;
            plugin.linksShortned++;
            return url;
        } catch (Exception exception) {
            return link;
        }
    }
}
