package net.kettlemc.discordbridge;

import net.kettlemc.discordbridge.config.Configuration;
import net.kettlemc.discordbridge.discord.DiscordBot;
import net.kettlemc.discordbridge.listener.AsyncChatListener;
import net.kettlemc.discordbridge.listener.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DiscordBridge extends JavaPlugin {

    private Configuration configuration;
    private DiscordBot bot;

    public void onEnable() {
        this.configuration = new Configuration();
        if (!this.configuration.token.equalsIgnoreCase("ENTER TOKEN HERE"))
            this.bot = new DiscordBot(this);
        else
            this.getLogger().warning("Please enter a token!");
        this.registerBukkit();
        new BukkitRunnable() {
            @Override
            public void run() {
                bot.sendMessage(configuration.channel, configuration.startupMessage);
            }
        }.runTaskLaterAsynchronously(this, 40L);
    }

    public void onDisable() {
        this.bot.sendMessage(configuration.channel, this.configuration.shutdownMessage);
        this.bot.shutdown();
    }

    private void registerBukkit() {
        Bukkit.getPluginManager().registerEvents(new AsyncChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this), this);
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public DiscordBot getBot() {
        return this.bot;
    }

}