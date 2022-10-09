package net.kettlemc.discordbridge;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.kettlemc.discordbridge.config.Configuration;
import net.kettlemc.discordbridge.discord.DiscordBot;
import net.kettlemc.discordbridge.listener.AsyncChatListener;
import net.kettlemc.discordbridge.listener.ClearLaggListener;
import net.kettlemc.discordbridge.listener.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class DiscordBridge extends JavaPlugin {

    private static DiscordBridge instance;
    private Configuration configuration;
    private DiscordBot bot;

    private boolean clearlaggEnabled = false;

    public void onEnable() {
        instance = this;
        this.configuration = new Configuration();
        if (!this.configuration.token.equalsIgnoreCase("ENTER TOKEN HERE")) {
            this.bot = new DiscordBot(this);
            this.bot.registerCommands();
        }
        else {
            this.getLogger().warning("Please enter a token!");
        }
        if (Bukkit.getPluginManager().getPlugin("ClearLagg") != null) {
            clearlaggEnabled = true;
        }
        this.registerBukkit();
        new BukkitRunnable() {
            @Override
            public void run() {
                bot.sendMessage(configuration.channel, configuration.startupMessage);
            }
        }.runTaskLaterAsynchronously(this, 40L);
    }

    public void onDisable() {
        this.bot.sendMessage(this.configuration.channel, this.configuration.shutdownMessage);
        this.bot.shutdown();
    }

    private void registerBukkit() {
        Bukkit.getPluginManager().registerEvents(new AsyncChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this), this);
        if (clearlaggEnabled)
            Bukkit.getPluginManager().registerEvents(new ClearLaggListener(this), this);
    }

    public static DiscordBridge getInstance() {
        return instance;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public DiscordBot getBot() {
        return this.bot;
    }

}