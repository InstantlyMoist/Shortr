package me.kyllian.shortr;

import me.kyllian.shortr.commands.ShortrCommand;
import me.kyllian.shortr.listeners.PlayerChatListener;
import me.kyllian.shortr.utils.MessageHandler;
import me.kyllian.shortr.utils.URLHandler;
import org.apache.log4j.BasicConfigurator;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Callable;

public class ShortrPlugin extends JavaPlugin {

    private MessageHandler messageHandler;
    private URLHandler urlHandler;

    public int linksShortned = 0;

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        BasicConfigurator.configure();

        getCommand("shortr").setExecutor(new ShortrCommand(this));

        Metrics metrics = new Metrics(this);

        metrics.addCustomChart(new Metrics.SingleLineChart("links_shortned", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return linksShortned;
            }
        }));

        messageHandler = new MessageHandler(this);
        urlHandler = new URLHandler(this);

        new PlayerChatListener(this);
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public URLHandler getUrlHandler() {
        return urlHandler;
    }
}
