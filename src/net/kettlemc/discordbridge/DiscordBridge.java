package net.kettlemc.discordbridge;

import net.kettlemc.discordbridge.config.Configuration;
import net.kettlemc.discordbridge.discord.DiscordBot;
import net.kettlemc.discordbridge.listener.AsyncChatListener;
import net.kettlemc.discordbridge.listener.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordBridge extends JavaPlugin {

    private Configuration configuration;
    private DiscordBot bot;

    public void onEnable() {
        this.configuration = new Configuration();
        if (!this.configuration.token.equalsIgnoreCase("ENTER TOKEN HERE"))
            this.bot = new DiscordBot(this);
        else
            this.getLogger().warning("Please enter a token!");
        this.registerListener();
    }

    public void onDisable() {
        this.bot.shutdown();
    }

    private void registerListener() {
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